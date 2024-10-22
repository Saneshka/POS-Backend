package com.saneshka.pos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saneshka.pos.entity.Category;
import com.saneshka.pos.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();

    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long catId) {
        return categoryRepository.findById(catId).orElse(null);
    }

    @Override
    public Category updateCategory(Long catId, Category category) {

        Category existingCategory = categoryRepository.findById(catId).orElse(null);

        if (existingCategory == null) {
            return null;
        } else {
            existingCategory.setCatName(category.getCatName());
            existingCategory.setDescription(category.getDescription());

            return categoryRepository.save(existingCategory);
        }
    }

}
