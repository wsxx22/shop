package com.makeup.order.domain.dto;

import com.makeup.product.domain.dto.ProductDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OrderDto {

    Long id;
    Long idCustomer;
    LocalDateTime orderTime;
    BigDecimal totalPrice;

    @Getter(AccessLevel.NONE)
    Set<ProductDto> products;

    public Set<ProductDto> getProducts(){
        return new HashSet<>(products);
    }
}
