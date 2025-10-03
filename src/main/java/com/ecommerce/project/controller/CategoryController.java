package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

//    USING AUTOWIRE
    @Autowired
     private CategoryService categoryService;

//    -------OR (USING CONSTUCTOR)-------

//    private CategoryService categoryService;
//
//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }

//    @RequestMapping(value="/public/categories", method = RequestMethod.GET)
//    OR
    @GetMapping("/public/categories")
    public ResponseEntity <List<Category>> getAllCategories()  {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

//    @RequestMapping(value = "/public/categories", method = RequestMethod.POST)
//    OR
    @PostMapping("/public/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>("Categories added...", HttpStatus.CREATED);
//            Or
//        return ResponseEntity.ok("categories added...");
//        or
//        return ResponseEntity.status(HttpStatus.OK).body("created...");
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable long categoryId) {
        String status = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

//    @RequestMapping(value = "/public/categories/{categoryId}", method = RequestMethod.PUT)
//    OR
    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory( @Valid @RequestBody Category category, @PathVariable Long categoryId) {
        Category savedCategory = categoryService.updateCategory(category, categoryId);
        return new ResponseEntity<>("Category with category id: " + categoryId + " is updated", HttpStatus.OK);
    }
}