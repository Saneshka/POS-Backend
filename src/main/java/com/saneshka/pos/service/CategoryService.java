package com.saneshka.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.saneshka.pos.entity.Category;

@Service
public interface CategoryService {
    List<Category> getAllCategories();

    Category createCategory(Category category);

    Category getCategoryById(Long catId);

    Category updateCategory(Long catId, Category category);
}
