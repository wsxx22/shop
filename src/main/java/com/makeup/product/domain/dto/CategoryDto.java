package com.makeup.product.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CategoryDto {
    Long id;
    String name;

    @Override
    public String toString() {
        return name;
    }
}
