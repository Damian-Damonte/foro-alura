package com.alura.foroAlura.mapper;

import com.alura.foroAlura.dto.category.CategoryResponse;
import com.alura.foroAlura.model.Category;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CategoryMapper {
    CategoryResponse categoryToCategoryResponse(Category category);
}
