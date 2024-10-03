package com.saneshka.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saneshka.pos.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
