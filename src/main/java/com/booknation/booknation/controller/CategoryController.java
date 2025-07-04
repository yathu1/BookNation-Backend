package com.booknation.booknation.controller;

import com.booknation.booknation.model.Category;
import com.booknation.booknation.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CategoryController {

    @Autowired
    private  CategoryService categoryService;

    @GetMapping("public/categories")
    public  ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("public/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        try {
            String status = categoryService.deleteCategory(categoryId);
            return ResponseEntity.status(HttpStatus.OK).body(status);
        }catch (ResponseStatusException e) {
            return new ResponseEntity<>( e.getReason(),e.getStatusCode());
        }

    }
    @PutMapping("admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory( @RequestBody Category category,@PathVariable Long categoryId) {
        try {
           Category savedCategory = categoryService.updateCategory(category, categoryId);
            return new ResponseEntity<>("Category updated successfully with category id " +categoryId, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }


}
