package com.tjetc.dao;

import com.tjetc.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    List<Product> selectList();

    List<Product> selectByProductName(String productName);

    Product selectById(Long productId);

    void insert(Product product);

    int deleteById(Long productId);

    int update(Product product);
}
