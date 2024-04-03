package com.tjetc.service;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Long id);

    List<Product> findByName(String productName);

    JsonResult save(Product product);

    boolean deleteById(Long id);

    boolean update(Product product);
}
