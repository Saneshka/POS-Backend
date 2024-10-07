package com.saneshka.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saneshka.pos.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
