package com.tjetc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 购买信息实体类，包含了购买信息的基本属性。
 */
@Data
public class Purchased {
    /** 用户ID */
    private long userid;
    /** 用户名 */
    private String username;
    /** 剩余金额 */
    private double left;
    /** 产品ID */
    private long productId;
    /** 产品名称 */
    private String productName;
    /** 购买日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime buyDate;
    /** 购买金额 */
    private double money;
    /** 利率 */
    private double yield;
}
