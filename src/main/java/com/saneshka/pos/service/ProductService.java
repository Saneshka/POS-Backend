package com.saneshka.pos.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.saneshka.pos.entity.Product;

@Service
public interface ProductService {
    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product getProductById(Long id);
}
