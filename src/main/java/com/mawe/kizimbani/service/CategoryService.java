package com.mawe.kizimbani.service;

import com.mawe.kizimbani.dao.CategoryDao;
import com.mawe.kizimbani.entity.Category;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public Category createCategory(Category category) {
        return categoryDao.save(category);
    }

    public List<Category> getAllCategories() {
        return (List<Category>) categoryDao.findAll();
    }

    public Optional<Category> getCategoryById(Integer categoryId) {
        return categoryDao.findById(categoryId);
    }

    public Category updateCategory(Integer categoryId, Category categoryDetails) {
        Category category = categoryDao.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));
        category.setCategoryName(categoryDetails.getCategoryName());
        return categoryDao.save(category);
    }

    public void deleteCategory(Integer categoryId) {
        Category category = categoryDao.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));
        categoryDao.delete(category);
    }

}
