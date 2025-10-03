package com.ecommerce.project.service;

import com.ecommerce.project.exception.APIException;
import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryReopsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List <Category> allCategories = categoryReopsitory.findAll();
        if(allCategories.isEmpty()) {
            throw new APIException("No categories created till now");
        }
        return allCategories;
    }

    @Override
    public void createCategory(Category category) {
//        category.setCategoryId(nextId ++);
        Category savedCategory = categoryReopsitory.findByCategoryName(category.getCategoryName());
        if(savedCategory != null) {
            throw new APIException("Category with the name " + category.getCategoryName() + " exists");
        }
        categoryReopsitory.save(category);
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> savedCategoryOptional = categoryReopsitory.findById(categoryId);

        Category savedCategory = savedCategoryOptional.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));

        category.setCategoryId(categoryId);
        savedCategory = categoryReopsitory.save(category);
        return savedCategory;
    }

    @Override
    public String deleteCategory(long categoryId) {
        Optional <Category> savedCategoryOptional = categoryReopsitory.findById(categoryId);

        Category category = savedCategoryOptional.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));

        categoryReopsitory.delete(category);
        return "Category with categoryId: " + categoryId + " deleted successfully...";
    }
}