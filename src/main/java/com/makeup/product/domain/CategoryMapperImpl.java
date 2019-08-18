package com.makeup.product.domain;

import com.makeup.product.domain.dto.CategoryDto;
import com.makeup.product.domain.exception.InvalidCategoryException;
import com.makeup.product.domain.exception.InvalidProductException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static com.makeup.product.domain.exception.InvalidCategoryException.CAUSE.CANT_CONVERT_TO_DTO;
import static com.makeup.product.domain.exception.InvalidCategoryException.CAUSE.DTO_SET_IS_EMPTY;

@Slf4j
public class CategoryMapperImpl implements CategoryMapper {
    @Override
    public Category toEntity(CategoryDto categoryDto) {
        Stream.of(categoryDto)
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> {
                    log.error(CANT_CONVERT_TO_DTO.getMessage());
                    throw new InvalidCategoryException(CANT_CONVERT_TO_DTO);
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
                    log.error(String.format(CANT_CONVERT_TO_DTO.getMessage(), DTO_SET_IS_EMPTY.getMessage()));
                    throw new InvalidCategoryException(CANT_CONVERT_TO_DTO);
                });
        Set<Category> categories = new HashSet<>();
        for (CategoryDto dto : categoriesDto){
            categories.add(toEntity(dto));
        }
        return categories;
    }
}
