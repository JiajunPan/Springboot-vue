package com.tjetc.dao;

import com.tjetc.entity.Purchased;
import com.tjetc.entity.Stat;

import java.time.LocalDateTime;
import java.util.List;

public interface PurchasedMapper {
    void insert(Purchased purchased);

    int delete(Purchased purchased);

    int deleteByProduct(Long productId);

    int updateByUser(Purchased purchased);

    List<Purchased> selectallBuy();

    List<Purchased> selectBuyByUser(Long userid);

    List<Purchased> selectBuyByProduct(Long productId);

    List<Purchased> selectBuyByDate(LocalDateTime date1, LocalDateTime date2);

    List<Stat> countBuyByUser();

    List<Stat> countBuyByProduct();

    Stat sumBuyByUser(Long userid);

    Stat sumBuyByProduct(Long productId);
    Stat countUserBuy(Long userid);
    List<Stat>sumBuyAllUser();
    List<Stat>sumBuyAll();

    Stat maxUser();

    Stat maxProduct();

    Stat minProduct();
}
