package com.tjetc.controller;

import com.tjetc.common.CheckPrivilege;
import com.tjetc.common.CreateDeleteJsonResult;
import com.tjetc.common.CreateUpdateJsonResult;
import com.tjetc.common.JsonResult;
import com.tjetc.entity.Product;
import com.tjetc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 产品控制器，处理产品相关的请求。
 */
@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 获取所有产品列表。
     *
     * @param session HttpSession对象。
     * @return 所有产品的JsonResult对象。
     */
    @RequestMapping("all")
    public JsonResult all(HttpSession session) {
        if (session == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        } else if (session.getAttribute("admin") == null && session.getAttribute("user") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }

        List<Product> products = productService.findAll();
        return new JsonResult(0, "", products);
    }

    /**
     * 根据产品ID获取产品信息。
     *
     * @param id      产品ID。
     * @param session HttpSession对象。
     * @return 产品信息的JsonResult对象。
     */
    @RequestMapping("byId/{id}")
    public JsonResult byId(@PathVariable("id") Long id, HttpSession session) {
        CheckPrivilege.checkPrivilege(session);
        Product product = productService.findById(id);
        return new JsonResult(0, "", product);
    }

    /**
     * 根据产品名称模糊查询产品列表。
     *
     * @param productName 产品名称。
     * @param session     HttpSession对象。
     * @return 符合条件的产品列表的JsonResult对象。
     */
    @RequestMapping("byName/{productName}")
    public JsonResult byName(@PathVariable("productName") String productName, HttpSession session) {
        CheckPrivilege.checkPrivilege(session);
        List<Product> products = productService.findByName(productName);
        return new JsonResult(0, "", products);

    }

    /**
     * 保存产品信息。
     *
     * @param product 产品对象。
     * @param session HttpSession对象。
     * @return 保存操作的结果的JsonResult对象。
     */
    @RequestMapping("save")
    public JsonResult save(Product product, HttpSession session) {
        CheckPrivilege.checkPrivilege(session);
        if (product.getProductName() == null || "".equals(product.getProductName().trim())) {
            return new JsonResult(1, "产品名不能为空", null);
        } else if (product.getYield() == 0 || "0".equals(product.getYield())) {
            return new JsonResult(1, "利率不能为空", null);
        }
        if (product.getIntroduce() == null || "".equals(product.getIntroduce().trim())) {
            product.setIntroduce("还没有介绍");
        }
        return productService.save(product);
    }

    /**
     * 根据产品ID删除产品。
     *
     * @param id      产品ID。
     * @param session HttpSession对象。
     * @return 删除操作的结果的JsonResult对象。
     */
    @RequestMapping("delete/{id}")
    public JsonResult delete(@PathVariable("id") Long id, HttpSession session) {
        CheckPrivilege.checkPrivilege(session);
        boolean flag = productService.deleteById(id);
        return CreateDeleteJsonResult.createDeleteJsonResult(flag);
    }

    /**
     * 处理IndexOutOfBoundsException异常。
     *
     * @param e 异常对象。
     * @return 处理异常后的JsonResult对象。
     */
    @ExceptionHandler({IndexOutOfBoundsException.class})
    public JsonResult handlerException1(Exception e) {
        e.printStackTrace();
        return new JsonResult<>(1, "记录为空", null);
    }

    /**
     * 修改产品信息。
     *
     * @param productId   产品ID。
     * @param session     HttpSession对象。
     * @param productName 产品名称。
     * @param introduce   产品介绍。
     * @param yield       利率。
     * @return 修改操作的结果的JsonResult对象。
     */
    @RequestMapping("change")
    public JsonResult change(Long productId, HttpSession session, String productName, @RequestParam(value = "introduce", required = false, defaultValue = "现在还没有简介") String introduce, double yield) {
        byId(productId, session);
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setIntroduce(introduce);
        product.setYield(yield);
        boolean flag = productService.update(product);
        return CreateUpdateJsonResult.createUpdateJsonResult(flag);
    }


}
