package com.makeup.product.domain;

import com.makeup.product.domain.dto.CategoryDto;
import com.makeup.product.domain.dto.CreateProductDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class ProductFactory {

    CategoryMapper categoryMapper;

    Product create(CreateProductDto createProductDto, Set<CategoryDto> categories){

        return Product.builder()
                .name(createProductDto.getName())
                .description(createProductDto.getDescription())
                .capacity(createProductDto.getCapacity())
                .price(createProductDto.getPrice())
                .categories(categoryMapper.toEntitySet(categories)).build();
    }

}
