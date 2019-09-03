package com.makeup.order.domain.dto;

import com.makeup.product.domain.dto.ProductDto;
import com.makeup.user.domain.dto.UserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateOrderDto {

    String customer;

    @Getter(value = AccessLevel.NONE)
    Set<ProductDto> products;

    public Set<ProductDto> getProducts(){
        return new HashSet<>(products);
    }

    public void changeAmount(int amount){
        products.forEach(productDto -> productDto.setAmount(amount));
    }
}
