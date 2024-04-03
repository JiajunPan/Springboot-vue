package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Purchased;
import com.tjetc.service.PurchasedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("purchased")
public class PurchasedController {
    @Autowired
    private PurchasedService purchasedService;

    @RequestMapping("all")
    public JsonResult all() {
        List<Purchased> purchaseds = purchasedService.findBuyAll();
        return new JsonResult(0, "", purchaseds);
    }

    @RequestMapping("byUser/{id}")
    public JsonResult byUser(@PathVariable("id") Long id) {
        List<Purchased> purchaseds = purchasedService.findBuyByUser(id);
        return new JsonResult(0, "", purchaseds);
    }

    @RequestMapping("byProduct/{productId}")
    public JsonResult byProduct(@PathVariable("productId") Long productId) {
        List<Purchased> purchaseds = purchasedService.findBuyByProduct(productId);
        return new JsonResult(0, "", purchaseds);
    }

    @RequestMapping("byDate")
    public JsonResult byDate(LocalDateTime date1, LocalDateTime date2) {
        List<Purchased> purchaseds = purchasedService.findBuyByDate(date1, date2);
        return new JsonResult(0, "", purchaseds);
    }

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

    @ExceptionHandler({IndexOutOfBoundsException.class, NullPointerException.class})
    public JsonResult handlerException1(Exception e) {
        e.printStackTrace();
        return new JsonResult<>(1, "记录为空", null);
    }

    @RequestMapping("deleteByUser")
    public JsonResult deleteByUser(Purchased purchased) {
        boolean bl = purchasedService.deleteByUser(purchased);
        JsonResult result;
        if (bl) {
            result = new JsonResult<>(0, "", null);
        } else {
            result = new JsonResult<>(1, "删除失败", null);
        }
        return result;
    }

    @RequestMapping("deleteByProduct/{id}")
    public JsonResult deleteByProduct(@PathVariable("id") Long id) {
        boolean bl = purchasedService.deleteByProductId(id);
        JsonResult result;
        if (bl) {
            result = new JsonResult<>(0, "", null);
        } else {
            result = new JsonResult<>(1, "删除失败", null);
        }
        return result;
    }

    @RequestMapping("change/{id}")
    public JsonResult change(@PathVariable("id") Long id, @RequestParam(value = "productId", required = true) Long productId, @RequestParam(value = "buyDate", required = true) LocalDateTime buyDate, @RequestParam(value = "money", required = true) double money, @RequestParam(value = "yield", required = true) double yield) {
        Purchased purchased = new Purchased();
        purchased.setUserid(id);
        purchased.setProductId(productId);
        purchased.setBuyDate(buyDate);
        purchased.setMoney(money);
        purchased.setYield(yield);
        boolean bl = purchasedService.updateByUser(purchased);
        if (bl) {
            return new JsonResult(0, "更新成功", bl);
        } else {
            return new JsonResult(1, "更新失败", bl);
        }
    }


    @RequestMapping("sumUser/{id}")
    public JsonResult sumUser(@PathVariable("id") Long id) {
        return new JsonResult(0, "", purchasedService.sumUser(id));
    }
    @RequestMapping("countUserBuy/{id}")
    public JsonResult countUserBuy(@PathVariable("id") Long id) {
        return new JsonResult(0, "", purchasedService.countUserBuy(id));
    }
    //列表统计类
    @RequestMapping("countUser")
    public JsonResult countUser() {
        return new JsonResult(0, "", purchasedService.countUser());
    }

    @RequestMapping("countProduct")
    public JsonResult countProduct() {
        return new JsonResult(0, "", purchasedService.countProduct());
    }

    @RequestMapping("sumUserAll")
    public JsonResult sumUserAll() {
        return new JsonResult(0, "", purchasedService.sumUserAll());
    }

    @RequestMapping("sumProductAll")
    public JsonResult sumProductAll() {
        return new JsonResult(0, "", purchasedService.sumProductAll());
    }

    //结束
    @RequestMapping("sumProduct/{productId}")
    public JsonResult sumProduct(@PathVariable("productId") Long productId) {
        return new JsonResult(0, "", purchasedService.sumProduct(productId));
    }

    @RequestMapping("computeIncome/{id}")
    public JsonResult computeIncome(@PathVariable("id") Long userid) {
        return new JsonResult(0, "", purchasedService.incomeUser(userid));
    }

    @RequestMapping("expectIncome/{id}")
    public JsonResult expectIncome(@PathVariable("id") Long userid) {
        return new JsonResult(0, "", purchasedService.expectIncomeUser(userid));
    }

    //单个统计类（买的最多的用户，卖的钱数最多的，卖的钱数最少的)
    @RequestMapping("maxUser")
    public JsonResult maxUser() {
        return new JsonResult(0, "", purchasedService.maxBuyUser());
    }

    @RequestMapping("maxProduct")
    public JsonResult maxProduct() {
        return new JsonResult(0, "", purchasedService.maxBuyProduct());
    }

    @RequestMapping("minProduct")
    public JsonResult minProduct() {
        return new JsonResult(0, "", purchasedService.minBuyProduct());
    }
}
