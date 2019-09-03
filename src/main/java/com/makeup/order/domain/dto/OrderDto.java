package com.makeup.order.domain.dto;

import com.makeup.product.domain.dto.ProductDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OrderDto {

    Long id;
    Long idCustomer;
    LocalDateTime orderTime;
    BigDecimal totalPrice;

    Set<ProductDto> products;
}
