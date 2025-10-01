package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService{
    private long nextId = 1l;
    private List<Category> categories = new ArrayList<>();

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId ++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(long categoryId) {
        Category category = categories.stream().filter(c -> c.getCategoryId().equals(categoryId)).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));

        categories.remove(category);
        return "Category with categoryId: " + categoryId + " deleted successfully...";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        // can do it in the same way as the deleteCategory().
        Optional <Category> categoryOptional = categories.stream().filter(c -> c.getCategoryId().equals(categoryId)).findFirst();
        if(categoryOptional.isPresent()) {
            Category existingCategory = categoryOptional.get();
            existingCategory.setCategoryName(category.getCategoryName());
            return existingCategory;
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found!");
        }
    }
}