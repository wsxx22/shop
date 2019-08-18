package com.makeup.product.domain;

import com.makeup.product.domain.dto.CategoryDto;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryDto categoryDto);

    Set<Category> toEntitySet(Set<CategoryDto> categories);
}
