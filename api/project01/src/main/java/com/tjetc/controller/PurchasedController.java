package com.tjetc.controller;

import com.tjetc.common.CreateDeleteJsonResult;
import com.tjetc.common.CreateUpdateJsonResult;
import com.tjetc.common.JsonResult;
import com.tjetc.entity.Purchased;
import com.tjetc.service.PurchasedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 购买记录控制器，处理购买记录相关的请求。
 */
@RestController
@RequestMapping("purchased")
public class PurchasedController {
    @Autowired
    private PurchasedService purchasedService;

    /**
     * 获取所有购买记录。
     *
     * @return 所有购买记录的JsonResult对象。
     */
    @RequestMapping("all")
    public JsonResult all() {
        List<Purchased> purchaseds = purchasedService.findBuyAll();
        return new JsonResult(0, "", purchaseds);
    }

    /**
     * 根据用户ID获取购买记录。
     *
     * @param id 用户ID。
     * @return 用户的购买记录的JsonResult对象。
     */
    @RequestMapping("byUser/{id}")
    public JsonResult byUser(@PathVariable("id") Long id) {
        List<Purchased> purchaseds = purchasedService.findBuyByUser(id);
        return new JsonResult(0, "", purchaseds);
    }

    /**
     * 根据产品ID获取购买记录。
     *
     * @param productId 产品ID。
     * @return 产品的购买记录的JsonResult对象。
     */
    @RequestMapping("byProduct/{productId}")
    public JsonResult byProduct(@PathVariable("productId") Long productId) {
        List<Purchased> purchaseds = purchasedService.findBuyByProduct(productId);
        return new JsonResult(0, "", purchaseds);
    }

    /**
     * 根据日期范围获取购买记录。
     *
     * @param date1 起始日期。
     * @param date2 结束日期。
     * @return 日期范围内的购买记录的JsonResult对象。
     */
    @RequestMapping("byDate")
    public JsonResult byDate(LocalDateTime date1, LocalDateTime date2) {
        List<Purchased> purchaseds = purchasedService.findBuyByDate(date1, date2);
        return new JsonResult(0, "", purchaseds);
    }

    /**
     * 保存购买记录。
     *
     * @param purchased 要保存的购买记录。
     * @return 保存操作的结果的JsonResult对象。
     */
    @RequestMapping("save")
    public JsonResult save(Purchased purchased) {
        if (purchased.getUserid() == 0 || "0".equals(purchased.getUserid())) {
            return new JsonResult(1, "用户id不能为空", null);
        } else if (purchased.getProductId() == 0 || "0".equals(purchased.getProductId())) {
            return new JsonResult(1, "产品id不能为空", null);
        } else if (purchased.getBuyDate() == null || "".equals(purchased.getBuyDate())) {
            return new JsonResult(1, "购买日期不能为空", null);
        } else if (purchased.getMoney() == 0 || "0".equals(purchased.getMoney())) {
            return new JsonResult(1, "购买金额不能为空", null);
        } else if (purchased.getYield() == 0 || "0".equals(purchased.getYield())) {
            return new JsonResult(1, "利率不能为空", null);
        } else return purchasedService.save(purchased);
    }

    /**
     * 处理IndexOutOfBoundsException和NullPointerException异常。
     *
     * @param e 异常对象。
     * @return 处理异常后的JsonResult对象。
     */
    @ExceptionHandler({IndexOutOfBoundsException.class, NullPointerException.class})
    public JsonResult handlerException1(Exception e) {
        e.printStackTrace();
        return new JsonResult<>(1, "记录为空", null);
    }

    /**
     * 根据用户删除购买记录。
     *
     * @param purchased 要删除的购买记录。
     * @return 删除操作的结果的JsonResult对象。
     */
    @RequestMapping("deleteByUser")
    public JsonResult deleteByUser(Purchased purchased) {
        boolean flag = purchasedService.deleteByUser(purchased);
        return CreateDeleteJsonResult.createDeleteJsonResult(flag);

    }

    /**
     * 根据产品ID删除购买记录。
     *
     * @param id 产品ID。
     * @return 删除操作的结果的JsonResult对象。
     */
    @RequestMapping("deleteByProduct/{id}")
    public JsonResult deleteByProduct(@PathVariable("id") Long id) {
        boolean flag = purchasedService.deleteByProductId(id);
        return CreateDeleteJsonResult.createDeleteJsonResult(flag);

    }

    /**
     * 修改购买记录。
     *
     * @param id        购买记录ID。
     * @param productId 产品ID。
     * @param buyDate   购买日期。
     * @param money     购买金额。
     * @param yield     利率。
     * @return 修改操作的结果的JsonResult对象。
     */
    @RequestMapping("change/{id}")
    public JsonResult change(@PathVariable("id") Long id, @RequestParam(value = "productId", required = true) Long productId, @RequestParam(value = "buyDate", required = true) LocalDateTime buyDate, @RequestParam(value = "money", required = true) double money, @RequestParam(value = "yield", required = true) double yield) {
        Purchased purchased = new Purchased();
        purchased.setUserid(id);
        purchased.setProductId(productId);
        purchased.setBuyDate(buyDate);
        purchased.setMoney(money);
        purchased.setYield(yield);
        boolean flag = purchasedService.updateByUser(purchased);
        return CreateUpdateJsonResult.createUpdateJsonResult(flag);
    }

    /**
     * 统计用户购买金额总和。
     *
     * @param id 用户ID。
     * @return 用户购买金额总和的JsonResult对象。
     */
    @RequestMapping("sumUser/{id}")
    public JsonResult sumUser(@PathVariable("id") Long id) {
        return new JsonResult(0, "", purchasedService.sumUser(id));
    }

    /**
     * 统计用户购买次数。
     *
     * @param id 用户ID。
     * @return 用户购买次数的JsonResult对象。
     */
    @RequestMapping("countUserBuy/{id}")
    public JsonResult countUserBuy(@PathVariable("id") Long id) {
        return new JsonResult(0, "", purchasedService.countUserBuy(id));
    }

    /**
     * 统计用户数量。
     *
     * @return 用户数量的JsonResult对象。
     */
    @RequestMapping("countUser")
    public JsonResult countUser() {
        return new JsonResult(0, "", purchasedService.countUser());
    }

    /**
     * 统计产品数量。
     *
     * @return 产品数量的JsonResult对象。
     */
    @RequestMapping("countProduct")
    public JsonResult countProduct() {
        return new JsonResult(0, "", purchasedService.countProduct());
    }

    /**
     * 统计所有用户购买金额总和。
     *
     * @return 所有用户购买金额总和的JsonResult对象。
     */
    @RequestMapping("sumUserAll")
    public JsonResult sumUserAll() {
        return new JsonResult(0, "", purchasedService.sumUserAll());
    }

    /**
     * 统计所有产品购买金额总和。
     *
     * @return 所有产品购买金额总和的JsonResult对象。
     */
    @RequestMapping("sumProductAll")
    public JsonResult sumProductAll() {
        return new JsonResult(0, "", purchasedService.sumProductAll());
    }

    /**
     * 统计指定产品的购买金额总和。
     *
     * @param productId 产品ID。
     * @return 产品购买金额总和的JsonResult对象。
     */
    @RequestMapping("sumProduct/{productId}")
    public JsonResult sumProduct(@PathVariable("productId") Long productId) {
        return new JsonResult(0, "", purchasedService.sumProduct(productId));
    }

    /**
     * 计算用户的收益。
     *
     * @param userid 用户ID。
     * @return 用户的收益的JsonResult对象。
     */
    @RequestMapping("computeIncome/{id}")
    public JsonResult computeIncome(@PathVariable("id") Long userid) {
        return new JsonResult(0, "", purchasedService.incomeUser(userid));
    }

    /**
     * 计算用户的预期收益。
     *
     * @param userid 用户ID。
     * @return 用户的预期收益的JsonResult对象。
     */
    @RequestMapping("expectIncome/{id}")
    public JsonResult expectIncome(@PathVariable("id") Long userid) {
        return new JsonResult(0, "", purchasedService.expectIncomeUser(userid));
    }

    /**
     * 查询购买最多的用户。
     *
     * @return 购买最多的用户的JsonResult对象。
     */
    @RequestMapping("maxUser")
    public JsonResult maxUser() {
        return new JsonResult(0, "", purchasedService.maxBuyUser());
    }

    /**
     * 查询购买金额最多的产品。
     *
     * @return 购买金额最多的产品的JsonResult对象。
     */
    @RequestMapping("maxProduct")
    public JsonResult maxProduct() {
        return new JsonResult(0, "", purchasedService.maxBuyProduct());
    }

    /**
     * 查询购买金额最少的产品。
     *
     * @return 购买金额最少的产品的JsonResult对象。
     */
    @RequestMapping("minProduct")
    public JsonResult minProduct() {
        return new JsonResult(0, "", purchasedService.minBuyProduct());
    }

}
