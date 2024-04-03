package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Product;
import com.tjetc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    //登陆信息类维护
    //所有列表
    @RequestMapping("all")
    public JsonResult all(HttpSession session) {
        if (session == null ) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        else if (session.getAttribute("admin")==null&&session.getAttribute("user")==null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }

        List<Product> products = productService.findAll();
        return new JsonResult(0, "", products);
    }

    @RequestMapping("byId/{id}")
    public JsonResult byId(@PathVariable("id") Long id,HttpSession session) {
        if (session == null || session.getAttribute("admin") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        if (session != null && session.getAttribute("user") != null) {
            return new JsonResult(-1, "用户禁止操作", null);
        }
        Product product = productService.findById(id);
        return new JsonResult(0, "", product);
    }

    @RequestMapping("byName/{productName}")
    public JsonResult byName(@PathVariable("productName") String productName,HttpSession session) {
        if (session == null || session.getAttribute("admin") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        if (session != null && session.getAttribute("user") != null) {
            return new JsonResult(-1, "用户禁止操作", null);
        }
        List<Product> products = productService.findByName(productName);
        return new JsonResult(0, "", products);

    }

    @RequestMapping("save")
    public JsonResult save(Product product,HttpSession session) {
        if (session == null || session.getAttribute("admin") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        if (session != null && session.getAttribute("user") != null) {
            return new JsonResult(-1, "用户禁止操作", null);
        }
        if (product.getProductName() == null || "".equals(product.getProductName().trim())) {
            return new JsonResult(1, "产品名不能为空", null);
        } else if (product.getYield() == 0 || "0".equals(product.getYield())) {
            return new JsonResult(1, "利率不能为空", null);
        }
        if(product.getIntroduce()==null||"".equals(product.getIntroduce().trim())){
            product.setIntroduce("还没有介绍");
        }
        return productService.save(product);
    }

    @RequestMapping("delete/{id}")
    public JsonResult delete(@PathVariable("id") Long id,HttpSession session) {
        if (session == null || session.getAttribute("admin") == null) {
            return new JsonResult(-1, "未登录或登录过期，请重新登录", null);
        }
        if (session != null && session.getAttribute("user") != null) {
            return new JsonResult(-1, "用户禁止操作", null);
        }
        boolean bl = productService.deleteById(id);
        JsonResult result;
        if (bl) {
            result = new JsonResult<>(0, "", null);
        } else {
            result = new JsonResult<>(1, "删除失败", null);
        }
        return result;
    }
    @ExceptionHandler({IndexOutOfBoundsException.class})
    public JsonResult handlerException1(Exception e) {
        e.printStackTrace();
        return new JsonResult<>(1, "记录为空", null);
    }
    @RequestMapping("change")
    public JsonResult change(Long productId,HttpSession session, String productName, @RequestParam(value = "introduce", required = false, defaultValue = "现在还没有简介") String introduce, double yield) {
        byId(productId,session);
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setIntroduce(introduce);
        product.setYield(yield);
        boolean bl = productService.update(product);
        if (bl) {
            return new JsonResult(0, "更新成功", bl);
        } else {
            return new JsonResult(1, "更新失败", bl);
        }
    }
}
