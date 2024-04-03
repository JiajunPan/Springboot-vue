package com.tjetc.service.impl;

import com.tjetc.common.JsonResult;
import com.tjetc.dao.ProductMapper;
import com.tjetc.entity.Product;
import com.tjetc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findAll() {
        return productMapper.selectList();
    }

    @Override
    public Product findById(Long id) {
        return productMapper.selectById(id);
    }

    @Override
    public List<Product> findByName(String productName) {
        return productMapper.selectByProductName(productName);
    }

    @Override
    public JsonResult save(Product product) {
        List<Product> products = productMapper.selectByProductName(product.getProductName());
        if (products.size() > 0) {
            return new JsonResult(1, "产品名重复，请换个产品名", null);
        }
        productMapper.insert(product);
        return new JsonResult(0, "新增产品成功", product.getProductName() + product.getYield());
    }

    @Override
    public boolean deleteById(Long id) {
        int rows = productMapper.deleteById(id);
        return rows > 0;
    }

    @Override
    public boolean update(Product product) {
        int rows = productMapper.update(product);
        return rows > 0;
    }
}
