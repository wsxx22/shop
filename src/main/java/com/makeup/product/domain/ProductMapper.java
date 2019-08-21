package com.makeup.product.domain;

import com.makeup.product.domain.dto.ProductDto;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
interface ProductMapper {

    ProductDto toDto(Product product);

    Set<ProductDto> toDtoSet(Set<Product> products);
}
