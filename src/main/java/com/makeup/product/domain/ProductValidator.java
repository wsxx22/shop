package com.makeup.product.domain;

import com.makeup.product.domain.dto.CreateProductDto;
import com.makeup.product.domain.exception.InvalidProductException;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Stream;

import static com.makeup.product.domain.exception.InvalidProductException.CAUSE.INVALID_PRODUCT_AMOUNT;
import static com.makeup.product.domain.exception.InvalidProductException.CAUSE.INVALID_PRODUCT_CAPACITY;
import static com.makeup.product.domain.exception.InvalidProductException.CAUSE.INVALID_PRODUCT_DESCRIPTION;
import static com.makeup.product.domain.exception.InvalidProductException.CAUSE.INVALID_PRODUCT_NAME;
import static com.makeup.product.domain.exception.InvalidProductException.CAUSE.INVALID_PRODUCT_PRICE;

class ProductValidator {

    void validDto(CreateProductDto createProductDto){
        validName(createProductDto.getName());
        validDescription(createProductDto.getDescription());
        validCapacity(createProductDto.getCapacity());
        validPrice(createProductDto.getPrice());
        validAmount(createProductDto.getAmount());
    }

    private void validAmount(int value) {
        Stream.of(value)
                .filter(amount -> amount >= 0)
                .filter(amount -> amount < 1000)
                .findFirst()
                .orElseThrow(() -> new InvalidProductException(INVALID_PRODUCT_AMOUNT));
    }

    private void validName(String name){
        Stream.of(name)
                .filter(n -> n.length() > 0)
                .filter(n -> n.length() <= 30)
                .findFirst()
                .orElseThrow(() -> new InvalidProductException(INVALID_PRODUCT_NAME));
    }

    private void validCapacity(double capacity){
        Stream.of(capacity)
                .filter(c -> c > 0.0)
                .filter(c -> c < 10)
                .findFirst()
                .orElseThrow(() -> new InvalidProductException(INVALID_PRODUCT_CAPACITY));
    }

    private void validPrice(BigDecimal price){
        Stream.of(price)
                .filter(p -> p.compareTo(new BigDecimal("10000")) <= 0)
                .filter(p -> p.compareTo(new BigDecimal("0")) > 0)
                .findFirst()
                .orElseThrow(() -> new InvalidProductException(INVALID_PRODUCT_PRICE));
    }

    private void validDescription(String description){
        Stream.of(description)
                .filter(p -> p.length() <= 200)
                .findFirst()
                .orElseThrow(() -> new InvalidProductException(INVALID_PRODUCT_DESCRIPTION));
    }

}
