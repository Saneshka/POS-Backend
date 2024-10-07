package com.saneshka.pos.service;

import org.springframework.stereotype.Service;

import com.saneshka.pos.entity.Order;

import java.util.List;;

@Service
public interface OrderService {
    List<Order> getAllOrders();

    Order createOrder(Order order);

}
