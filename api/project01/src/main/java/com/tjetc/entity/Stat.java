package com.tjetc.entity;

import lombok.Data;

@Data
public class Stat {
    //    统计类对象
    private long userid;
    private long productId;
    private double sumMoney;
    private String productName;
    private int cnt;
}
