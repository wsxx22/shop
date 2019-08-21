package com.makeup.product.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateProductDto {
    String name;
    String description;
    double capacity;
    BigDecimal price;
    int amount;

    Set<CategoryDto> categories;

    @Override
    public String toString() {
        return "Name: " + name
                + ", Capacity: " + capacity
                + ", Price: " + price
                + ", Amount: " + amount
                + ", Categories:" + categories.toString();
    }
}
