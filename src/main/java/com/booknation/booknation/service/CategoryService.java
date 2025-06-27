package com.booknation.booknation.service;

import com.booknation.booknation.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    List<Category> getAllCategories();
    void createCategory(Category category);


}
