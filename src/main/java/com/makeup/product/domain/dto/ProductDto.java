package com.makeup.product.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ProductDto {
    Long id;
    String name;
    String description;
    double capacity;
    BigDecimal price;
    int amount;

    @Getter(value = AccessLevel.NONE)
    Set<CategoryDto> categories;

    public Set<CategoryDto> getCategories() {
        return new HashSet<>(categories);
    }

    public void setAmount(int amount){
        this.amount = amount;
    }
}
