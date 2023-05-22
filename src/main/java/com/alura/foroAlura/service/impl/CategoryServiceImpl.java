package com.alura.foroAlura.service.impl;

import com.alura.foroAlura.dto.category.CategoryRequest;
import com.alura.foroAlura.dto.category.CategoryResponse;
import com.alura.foroAlura.exception.BadRequestException;
import com.alura.foroAlura.exception.NotFoundException;
import com.alura.foroAlura.mapper.CategoryMapper;
import com.alura.foroAlura.model.Category;
import com.alura.foroAlura.repository.CategoryRepository;
import com.alura.foroAlura.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Category with id " + id + " not found"));
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::categoryToCategoryResponse).toList();
    }

    @Override
    @Transactional
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        String categoryName = categoryRequest.name();
        if(categoryRepository.findByName(categoryName).isPresent())
            throw new BadRequestException("There is already a category with the name '" + categoryName + "'");
        Category category = categoryRepository.save(Category.builder().name(categoryRequest.name()).build());
        return categoryMapper.categoryToCategoryResponse(category);
    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = getCategoryById(id);
        String categoryName = categoryRequest.name();
        Category categoryByName = categoryRepository.findByName(categoryName).orElse(null);
        if(categoryByName != null && !(Objects.equals(categoryByName.getId(), id)))
            throw new BadRequestException("There is already a category with the name '" + categoryName + "'");
        category.setName(categoryName);
        return categoryMapper.categoryToCategoryResponse(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        getCategoryById(id);
        categoryRepository.deleteById(id);
    }
}
