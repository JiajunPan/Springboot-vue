package com.tjetc.service;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Purchased;
import com.tjetc.entity.Stat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 购买记录相关操作的服务接口。
 */
public interface PurchasedService {
    List<Purchased> findBuyByUser(Long userid);

    List<Purchased> findBuyAll();

    List<Purchased> findBuyByProduct(Long productId);

    List<Purchased> findBuyByDate(LocalDateTime date1, LocalDateTime date2);

    JsonResult save(Purchased purchased);

    boolean deleteByUser(Purchased purchased);

    boolean deleteByProductId(Long productId);

    boolean updateByUser(Purchased purchased);

    List<Stat> countUser();

    List<Stat> countProduct();

    double sumUser(Long userid);

    int countUserBuy(Long userid);

    double sumProduct(Long productId);

    List<Stat> sumUserAll();

    List<Stat> sumProductAll();

    Stat incomeUser(Long userid);

    Stat expectIncomeUser(Long userid);

    Stat maxBuyUser();

    Stat maxBuyProduct();

    Stat minBuyProduct();
}
