package com.makeup.product.domain;

import com.makeup.product.domain.dto.ProductDto;
import java.util.Set;

interface ProductMapper {
    ProductDto toDto(Product product);
    Set<ProductDto> toDtoSet(Set<Product> products);
}
