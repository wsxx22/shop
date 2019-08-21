package com.makeup.product.domain;

import com.makeup.product.domain.dto.CreateProductDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
class ProductFactory {

    CategoryMapperImpl categoryMapper;

    Product create(CreateProductDto createProductDto){
        return Product.builder()
                .name(createProductDto.getName())
                .description(createProductDto.getDescription())
                .capacity(createProductDto.getCapacity())
                .price(createProductDto.getPrice())
                .amount(createProductDto.getAmount())
                .categories(categoryMapper.toEntitySet(createProductDto.getCategories())).build();

    }
}
