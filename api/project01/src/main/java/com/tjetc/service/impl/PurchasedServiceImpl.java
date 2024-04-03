package com.tjetc.service.impl;

import com.tjetc.common.JsonResult;
import com.tjetc.dao.PurchasedMapper;
import com.tjetc.dao.UserMapper;
import com.tjetc.entity.Purchased;
import com.tjetc.entity.Stat;
import com.tjetc.entity.User;
import com.tjetc.service.PurchasedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PurchasedServiceImpl implements PurchasedService {
    @Autowired
    private PurchasedMapper purchasedMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Purchased> findBuyByUser(Long userid) {
        return purchasedMapper.selectBuyByUser(userid);
    }

    @Override
    public List<Purchased> findBuyAll() {
        return purchasedMapper.selectallBuy();
    }

    @Override
    public List<Purchased> findBuyByProduct(Long productId) {
        return purchasedMapper.selectBuyByUser(productId);
    }

    @Override
    public List<Purchased> findBuyByDate(LocalDateTime date1, LocalDateTime date2) {
        return purchasedMapper.selectBuyByDate(date1, date2);
    }
//    buy买入操作
    @Transactional
    @Override
    public JsonResult save(Purchased purchased) {
        User user=new User();
        user.setUserid(purchased.getUserid());
        if(purchased.getLeft()-purchased.getMoney()<0)
        {
            throw new RuntimeException("余额不足，异常");
        }
        user.setBalance(purchased.getLeft()-purchased.getMoney());
        userMapper.updateBalance(user);
        purchasedMapper.insert(purchased);
        return new JsonResult(0, "购买成功", purchased.getUserid() + purchased.getProductId() + purchased.getMoney() + purchased.getYield());
    }
//sell卖出操作
@Transactional
    @Override
    public boolean deleteByUser(Purchased purchased) {
//        设置user，余额
    User user=new User();
    user.setUserid(purchased.getUserid());
//    计算收益
    LocalDateTime localDateTime=LocalDateTime.now();
    LocalDateTime localDateTime1=purchased.getBuyDate();
    double yield=purchased.getYield();
    double base=purchased.getLeft();
    double buy=purchased.getMoney();
    long month= ChronoUnit.MONTHS.between(localDateTime1,localDateTime);
    double income=buy*yield*month/12;
    double allMoney=buy+income+base;
//    加和写入余额
user.setBalance(allMoney);
    userMapper.updateBalance(user);
//    删除购买记录
        int rows = purchasedMapper.delete(purchased);
        return rows > 0;
    }

    @Override
    public boolean deleteByProductId(Long productId) {
        int rows = purchasedMapper.deleteByProduct(productId);
        return rows > 0;
    }

    @Override
    public boolean updateByUser(Purchased purchased) {
        int rows = purchasedMapper.updateByUser(purchased);
        return rows > 0;
    }

    @Override
    public  List<Stat> countUser() {
        return purchasedMapper.countBuyByUser();

    }

    @Override
    public List<Stat> countProduct() {
        return purchasedMapper.countBuyByProduct();
    }

    @Override
    public double sumUser(Long userid) {
        Stat stat = purchasedMapper.sumBuyByUser(userid);
        return stat.getSumMoney();
    }

    @Override
    public int countUserBuy(Long userid) {
        Stat stat=purchasedMapper.countUserBuy(userid);
        return stat.getCnt();
    }

    @Override
    public double sumProduct(Long productId) {
        Stat stat = purchasedMapper.sumBuyByProduct(productId);
        return stat.getSumMoney();
    }
    @Override
    public List<Stat> sumUserAll() {
        return purchasedMapper.sumBuyAllUser();
    }
    @Override
    public List<Stat> sumProductAll() {
        return purchasedMapper.sumBuyAll();
    }

    //计算现期收益
    @Override
    public Stat incomeUser(Long userid) {
       List<Purchased>purchases=purchasedMapper.selectBuyByUser(userid);
        Stat stat=new Stat();
        double income=0;
        LocalDateTime localDateTime=LocalDateTime.now();
       if(purchases.size()>0){
            for(int i=0;i<purchases.size();i++)
            {
                double buy=purchases.get(i).getMoney();
                double yield=purchases.get(i).getYield();
                LocalDateTime localDateTime1=purchases.get(i).getBuyDate();
                long month= ChronoUnit.MONTHS.between(localDateTime1,localDateTime);
                income+=buy*yield*month/12;
            }
            stat.setUserid(userid);
            stat.setSumMoney(income);
       }
       else{
           stat.setUserid(userid);
           stat.setSumMoney(0);
       }
       return stat;
    }
//计算预期收益
    @Override
    public Stat expectIncomeUser(Long userid) {
        List<Purchased>purchases=purchasedMapper.selectBuyByUser(userid);
        Stat stat=new Stat();
        double income=0;
        LocalDateTime localDateTime=LocalDateTime.now();
        if(purchases.size()>0){
            for(int i=0;i<purchases.size();i++)
            {
                double buy=purchases.get(i).getMoney();
                double yield=purchases.get(i).getYield();
                income+=buy*yield;
            }
            stat.setUserid(userid);
            stat.setSumMoney(income);
        }
        else{
            stat.setUserid(userid);
            stat.setSumMoney(0);
        }
        return stat;
    }

    @Override
    public Stat maxBuyUser() {
        return purchasedMapper.maxUser();
    }

    @Override
    public Stat maxBuyProduct() {
        return purchasedMapper.maxProduct();
    }

    @Override
    public Stat minBuyProduct() {
        return purchasedMapper.minProduct();
    }
}
