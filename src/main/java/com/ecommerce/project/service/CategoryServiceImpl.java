package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryReopsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService {
//    private long nextId = 1l;
//    private List<Category> categories = new ArrayList<>();
    @Autowired
    private CategoryReopsitory categoryReopsitory;

    @Override
    public List<Category> getAllCategories() {
        return categoryReopsitory.findAll();
    }

    @Override
    public void createCategory(Category category) {
//        category.setCategoryId(nextId ++);
        categoryReopsitory.save(category);
    }

    @Override
    public String deleteCategory(long categoryId) {
        Optional <Category> savedCategoryOptional = categoryReopsitory.findById(categoryId);

        Category category = savedCategoryOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));

        categoryReopsitory.delete(category);
        return "Category with categoryId: " + categoryId + " deleted successfully...";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> savedCategoryOptional = categoryReopsitory.findById(categoryId);

        Category savedCategory = savedCategoryOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));

        category.setCategoryId(categoryId);
        savedCategory = categoryReopsitory.save(category);
        return savedCategory;
    }
}