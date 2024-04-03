package com.tjetc.entity;


import lombok.Data;

@Data
public class Product {
    private long productId;
    private String productName;
    private String introduce;
    private double yield;
}
