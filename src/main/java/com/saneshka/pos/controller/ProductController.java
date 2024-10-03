package com.saneshka.pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saneshka.pos.dto.ProductReqDTO;
import com.saneshka.pos.entity.Category;
import com.saneshka.pos.entity.Product;
import com.saneshka.pos.service.CategoryService;
import com.saneshka.pos.service.ProductService;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        return ResponseEntity.status(200).body(productList);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductReqDTO productReqDTO) {
        try {
            Product newProduct = new Product();
            newProduct.setProductName(productReqDTO.getProductName());
            newProduct.setPrice(productReqDTO.getPrice());
            newProduct.setDescription(productReqDTO.getDescription());
            newProduct.setQty(productReqDTO.getQty());

            Category category = categoryService.getCategoryById(productReqDTO.getCategoryId());

            newProduct.setCategory(category);

            Product createdProduct = productService.createProduct(newProduct);
            return ResponseEntity.status(201).body(createdProduct);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(400).body(null);
        }
    }
}
