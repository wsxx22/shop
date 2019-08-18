package com.makeup.product.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
public class ProductDto {
    Long id;
    String name;
    String description;
    double amount;
    BigDecimal price;
}
