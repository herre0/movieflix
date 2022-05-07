package com.mert.movieapp.controller;


import com.mert.movieapp.model.Category;
import com.mert.movieapp.repository.CategoryRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/category")
    public Iterable<Category> getCategory() {
        return categoryRepository.findAll();
    }

    @GetMapping("/category/{categoryId}")
    public Category getCategoryById(@PathVariable int categoryId) {
        return categoryRepository.findById(categoryId).get();
    }

    @PostMapping("/category")
    public ResponseEntity<String> addCategory(@RequestBody Category category) {
        categoryRepository.save(category);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The category is inserted successfully");
    }

    @PostMapping("/category/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable int categoryId, @RequestBody Category newCategory) {
        Category category = categoryRepository.findById(categoryId).get();
        category.setCategoryName(newCategory.getCategoryName());        

        categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The new category was updated successfully.");
    }
}