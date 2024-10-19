package com.saneshka.pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ArrayList;
import org.springframework.transaction.annotation.Transactional;

import com.saneshka.pos.dto.OrderDTO;
import com.saneshka.pos.entity.Order;
import com.saneshka.pos.entity.Product;
import com.saneshka.pos.entity.Stock;
import com.saneshka.pos.service.OrderService;
import com.saneshka.pos.service.ProductService;
import com.saneshka.pos.service.StockService;

@RestController
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();

        return ResponseEntity.status(200).body(orders);
    }

    @PostMapping("/orders")
    @Transactional
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        try {

            Order order = new Order();
            order.setTotalPrice(0.0);
            List<Product> orderedProducts = new ArrayList<>();

            List<Long> productIds = orderDTO.getProductIds();
            for (Long productId : productIds) {
                Product product = productService.getProductById(productId);

                if (product != null) {
                    orderedProducts.add(product);

                    Stock newStock = new Stock();
                    Stock existingStock = product.getStock();

                    Integer existingQty = existingStock.getQty();
                    if (existingQty <= 0) {
                        throw new RuntimeException("Insufficient stock for product: " + product.getProductName());
                    }

                    Integer newQty = existingQty - 1;

                    newStock.setId(existingStock.getId());
                    newStock.setQty(newQty);
                    newStock.setProduct(product);

                    stockService.updateStock(existingStock.getId(), newStock);

                    order.setTotalPrice(order.getTotalPrice() + product.getPrice());
                } else {
                    throw new RuntimeException("Product with ID " + productId + " not found.");
                }
            }

            order.setOrderedProducts(orderedProducts);
            orderService.createOrder(order);

            return ResponseEntity.status(201).body(order);

        } catch (RuntimeException e) {

            System.err.println("Error creating order: " + e.getMessage());

            // return ResponseEntity.status(400).body(null);
            throw e;
        }
    }

}
