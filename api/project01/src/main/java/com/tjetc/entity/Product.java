package com.tjetc.entity;

import lombok.Data;

/**
 * 产品实体类，包含产品的基本信息。
 */
@Data
public class Product {
    /** 产品ID */
    private long productId;
    /** 产品名称 */
    private String productName;
    /** 产品介绍 */
    private String introduce;
    /** 产品利率 */
    private double yield;
}
