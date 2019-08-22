package com.makeup.product.domain;

import com.makeup.product.domain.dto.CategoryDto;
import org.mapstruct.Mapper;

import java.util.Set;

abstract class CategoryMapper {

    abstract Category toEntity(CategoryDto categoryDto);

    abstract Set<Category> toEntitySet(Set<CategoryDto> categories);

    abstract CategoryDto toDto(Category category);

    abstract Set<CategoryDto> toDtoSet(Set<Category> categories);
}
