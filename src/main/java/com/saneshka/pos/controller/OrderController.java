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

import com.saneshka.pos.dto.OrderDTO;
import com.saneshka.pos.entity.Order;
import com.saneshka.pos.entity.Product;
import com.saneshka.pos.service.OrderService;
import com.saneshka.pos.service.ProductService;

@RestController
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();

        return ResponseEntity.status(200).body(orders);
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {

        try {
            Order order = new Order();
            order.setTotalPrice(0.0);
            List<Product> orderedProducts = new ArrayList<>();

            List<Long> productIds = orderDTO.getProductIds();
            productIds.forEach(productId -> {

                Product product = productService.getProductById(productId);

                if (product != null) {
                    orderedProducts.add(product);
                    order.setTotalPrice(order.getTotalPrice() + product.getPrice());
                }

            });

            order.setOrderedProducts(orderedProducts);

            orderService.createOrder(order);

            return ResponseEntity.status(201).body(order);

        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
            // TODO: handle exception
        }
    }
}
