package com.alura.foroAlura.service;

import com.alura.foroAlura.dto.category.CategoryRequest;
import com.alura.foroAlura.dto.category.CategoryResponse;
import com.alura.foroAlura.model.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Long id);
    List<CategoryResponse> getAllCategories();
    CategoryResponse saveCategory(CategoryRequest categoryRequest);
    CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest);
    void deleteCategory(Long id);
}
