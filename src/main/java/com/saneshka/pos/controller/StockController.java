package com.saneshka.pos.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.saneshka.pos.dto.StockDTO;
import com.saneshka.pos.entity.Product;
import com.saneshka.pos.entity.Stock;
import com.saneshka.pos.service.ProductService;
import com.saneshka.pos.service.StockService;

@RestController
public class StockController {
    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    @GetMapping("/stocks")
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stockList = stockService.getAllStocks();
        return ResponseEntity.status(200).body(stockList);
    }

    @PostMapping("/stocks")
    public ResponseEntity<Stock> createStock(@RequestBody StockDTO stockDTO) {
        try {
            Stock newStock = new Stock();
            newStock.setQty(stockDTO.getQty());

            Product product = productService.getProductById(stockDTO.getProductId());
            newStock.setProduct(product);

            Stock createdStock = stockService.createStock(newStock);

            return ResponseEntity.status(200).body(createdStock);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @PutMapping("/stocks/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long id, @RequestBody StockDTO stockDTO) {
        try {
            Stock existingStock = stockService.geStockById(id);

            if (existingStock == null) {
                return ResponseEntity.status(400).body(null);
            } else {
                existingStock.setQty(stockDTO.getQty());

                Product product = productService.getProductById(stockDTO.getProductId());
                existingStock.setProduct(product);

                Stock updatedStock = stockService.updateStock(id, existingStock);
                return ResponseEntity.status(200).body(updatedStock);
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @DeleteMapping("/stocks/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable Long id) {
        try {
            stockService.deleteStock(id);
            return ResponseEntity.status(200).body("Stock Deleted");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

}
