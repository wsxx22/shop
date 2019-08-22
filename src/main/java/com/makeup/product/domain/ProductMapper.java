package com.makeup.product.domain;

import com.makeup.product.domain.dto.ProductDto;
import org.mapstruct.Mapper;

import java.util.Set;

abstract class ProductMapper {

    public abstract ProductDto toDto(Product product);

    public abstract Set<ProductDto> toDtoSet(Set<Product> products);
}
