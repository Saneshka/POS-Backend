package com.saneshka.pos.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saneshka.pos.entity.Product;
import com.saneshka.pos.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByProduct(Product product);
}
