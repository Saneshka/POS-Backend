package com.saneshka.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saneshka.pos.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
