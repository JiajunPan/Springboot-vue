package com.tjetc.entity;

import lombok.Data;

/**
 * 统计信息实体类，包含了统计信息的基本属性。
 */
@Data
public class Stat {
    /** 用户ID */
    private long userid;
    /** 产品ID */
    private long productId;
    /** 总金额 */
    private double sumMoney;
    /** 产品名称 */
    private String productName;
    /** 计数 */
    private int cnt;
}
