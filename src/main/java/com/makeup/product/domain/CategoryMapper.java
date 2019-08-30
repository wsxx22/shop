package com.makeup.product.domain;

import com.makeup.product.domain.dto.CategoryDto;
import java.util.Set;

interface CategoryMapper {
    Category toEntity(CategoryDto categoryDto);
    Set<Category> toEntitySet(Set<CategoryDto> categories);
    CategoryDto toDto(Category category);
    Set<CategoryDto> toDtoSet(Set<Category> categories);
}
