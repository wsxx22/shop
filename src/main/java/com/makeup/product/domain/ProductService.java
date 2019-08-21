package com.makeup.product.domain;

import com.makeup.product.domain.dto.CategoryDto;
import com.makeup.product.domain.dto.CreateProductDto;
import com.makeup.product.domain.dto.ProductDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
class ProductService {

    private final static String PRODUCT_SAVE = "Product added. %s";

    ProductRepository productRepository;
    ProductFactory productFactory;
    ProductValidator productValidator;
    ProductMapperImpl productMapper;

    void addProduct (CreateProductDto createProductDto){
        productValidator.validDto(createProductDto);
        productRepository.save(productFactory.create(createProductDto));
        log.info(String.format(PRODUCT_SAVE, createProductDto.toString()));
    }

    Set<ProductDto> findAllProducts(){
        return productMapper.toDtoSet(productRepository.findAll());
    }
}
