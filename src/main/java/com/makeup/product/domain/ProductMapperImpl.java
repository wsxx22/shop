package com.makeup.product.domain;

import com.makeup.product.domain.dto.ProductDto;
import com.makeup.product.domain.exception.InvalidProductException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static com.makeup.product.domain.exception.InvalidProductException.CAUSE.CANT_CONVERT_TO_DTO;

@Slf4j
class ProductMapperImpl implements ProductMapper {
    @Override
    public ProductDto toDto(Product product) {
        Stream.of(product)
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> {
                    log.error(CANT_CONVERT_TO_DTO.getMessage());
                    throw new InvalidProductException(CANT_CONVERT_TO_DTO);
                });

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .capacity(product.getCapacity())
                .price(product.getPrice())
                .amount(product.getAmount()).build();
    }

    @Override
    public Set<ProductDto> toDtoSet(Set<Product> products) {
        Set<ProductDto> set = new HashSet<>(products.size());
        products.forEach(product -> set.add(toDto(product)));
        return set;
    }


}
