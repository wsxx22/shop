package com.makeup.product.domain;

import com.makeup.product.domain.dto.CategoryDto;
import com.makeup.product.domain.exception.InvalidCategoryException;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.processing.Generated;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static com.makeup.product.domain.exception.InvalidCategoryException.CAUSE.*;

@Slf4j
class CategoryMapperImpl implements CategoryMapper {
    @Override
    public Category toEntity(CategoryDto categoryDto) {
        Stream.of(categoryDto)
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> {
                    log.error(CANT_CONVERT_TO_ENTITY.getMessage());
                    throw new InvalidCategoryException(CANT_CONVERT_TO_ENTITY);
                });
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName()).build();
    }

    @Override
    public Set<Category> toEntitySet(Set<CategoryDto> categoriesDto) {
        Stream.of(categoriesDto)
                .filter(set -> !set.isEmpty())
                .findFirst()
                .orElseThrow(() -> {
                    log.error(String.format(CANT_CONVERT_TO_ENTITY.getMessage(), SET_IS_EMPTY.getMessage()));
                    throw new InvalidCategoryException(SET_IS_EMPTY);
                });
        Set<Category> categories = new HashSet<>();
        for (CategoryDto dto : categoriesDto){
            categories.add(toEntity(dto));
        }
        return categories;
    }

    @Override
    public CategoryDto toDto(Category category) {
        Stream.of(category)
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> {
                    log.error(CANT_CONVERT_TO_DTO.getMessage());
                    throw new InvalidCategoryException(CANT_CONVERT_TO_DTO);
                });
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName()).build();
    }

    @Override
    public Set<CategoryDto> toDtoSet(Set<Category> categories) {
        Stream.of(categories)
                .filter(set -> !set.isEmpty())
                .findFirst()
                .orElseThrow(() -> {
                    log.error(String.format(CANT_CONVERT_TO_DTO.getMessage(), SET_IS_EMPTY.getMessage()));
                    throw new InvalidCategoryException(SET_IS_EMPTY);
                });
        Set<CategoryDto> categoriesDto = new HashSet<>();
        for (Category c : categories){
            categoriesDto.add(toDto(c));
        }
        return categoriesDto;
    }
}
