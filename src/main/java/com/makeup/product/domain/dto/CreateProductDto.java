package com.makeup.product.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class CreateProductDto {
    String name;
    String description;
    double amount;
    BigDecimal price;
}
