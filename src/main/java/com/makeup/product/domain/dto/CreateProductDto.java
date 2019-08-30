package com.makeup.product.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.HashSet;
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

    @Getter(AccessLevel.NONE)
    Set<CategoryDto> categories;

    public Set<CategoryDto> getCategories(){
        return new HashSet<>(categories);
    }

    @Override
    public String toString() {
        return "Name: " + name
                + ", Capacity: " + capacity
                + ", Price: " + price
                + ", Amount: " + amount
                + ", Categories:" + categories.toString();
    }
}
