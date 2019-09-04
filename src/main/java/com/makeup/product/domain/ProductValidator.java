package com.makeup.product.domain;

import com.makeup.product.domain.dto.CreateProductDto;
import com.makeup.product.domain.dto.ProductDto;
import com.makeup.product.domain.exception.InvalidProductException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.makeup.product.domain.exception.InvalidProductException.CAUSE.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
class ProductValidator {

    ProductRepository productRepository;

    void validCreateDto(CreateProductDto createProductDto){
        validName(createProductDto.getName());
        validDescription(createProductDto.getDescription());
        validCapacity(createProductDto.getCapacity());
        validPrice(createProductDto.getPrice());
        validAmount(createProductDto.getAmount());
    }

    void validDtoToUpdate(ProductDto productDto){
        validLengthName(productDto.getName());
        validDescription(productDto.getDescription());
        validCapacity(productDto.getCapacity());
        validPrice(productDto.getPrice());
        validAmount(productDto.getAmount());
    }

    private void validAmount(int value) {
        if (value < 0 || value > 1000)
            throw new InvalidProductException(INVALID_PRODUCT_AMOUNT);
//        Optional.of(value)
//                .filter(amount -> amount >= 0)
//                .filter(amount -> amount < 1000)
//                .orElseThrow(() -> new InvalidProductException(INVALID_PRODUCT_AMOUNT));
    }

    private void validName(String name){
        productRepository.findByName(name)
                .ifPresent(product -> {
                    throw new InvalidProductException(PRODUCT_NAME_EXISTS);
                });
        validLengthName(name);
//        Stream.of(name)
//                .filter(n -> n.length() > 0)
//                .filter(n -> n.length() <= 30)
//                .findFirst()
//                .orElseThrow(() -> new InvalidProductException(INVALID_PRODUCT_NAME));
    }

    private void validLengthName(String name){
        if (name.length() == 0 || name.length() > 30) {
            throw new InvalidProductException(INVALID_PRODUCT_NAME);
        }
    }

    private void validCapacity(double capacity){
        if (capacity == 0.0 || capacity > 10.0)
            throw new InvalidProductException(INVALID_PRODUCT_CAPACITY);
//        Stream.of(capacity)
//                .filter(c -> c > 0.0)
//                .filter(c -> c <= 10)
//                .findFirst()
//                .orElseThrow(() -> new InvalidProductException(INVALID_PRODUCT_CAPACITY));
    }

    private void validPrice(BigDecimal price){
        Stream.of(price)
                .filter(p -> p.compareTo(new BigDecimal("10000")) <= 0)
                .filter(p -> p.compareTo(new BigDecimal("0")) > 0)
                .findFirst()
                .orElseThrow(() -> new InvalidProductException(INVALID_PRODUCT_PRICE));
    }

    private void validDescription(String description){
        if (description.length() > 200)
            throw new InvalidProductException(INVALID_PRODUCT_DESCRIPTION);
//        Stream.of(description)
//                .filter(p -> p.length() <= 200)
//                .findFirst()
//                .orElseThrow(() -> new InvalidProductException(INVALID_PRODUCT_DESCRIPTION));
    }
}
