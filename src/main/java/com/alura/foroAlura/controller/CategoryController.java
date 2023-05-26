package com.alura.foroAlura.controller;

import com.alura.foroAlura.dto.category.CategoryRequest;
import com.alura.foroAlura.dto.category.CategoryResponse;
import com.alura.foroAlura.mapper.CategoryMapper;
import com.alura.foroAlura.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryMapper.categoryToCategoryResponse(categoryService.getCategoryById(id)));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping
    @Operation(
            summary = "This endpoint can only be accessed by users with the admin role"
    )
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveCategory(categoryRequest));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "This endpoint can only be accessed by users with the admin role"
    )
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable Long id, @RequestBody @Valid CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "This endpoint can only be accessed by users with the admin role"
    )
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
