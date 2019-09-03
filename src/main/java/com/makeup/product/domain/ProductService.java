package com.makeup.product.domain;

import com.makeup.product.domain.dto.CategoryDto;
import com.makeup.product.domain.dto.CreateProductDto;
import com.makeup.product.domain.dto.ProductDto;
import com.makeup.product.domain.exception.InvalidProductException;
import com.makeup.product.domain.query.ProductQueryDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

import static com.makeup.product.domain.exception.InvalidProductException.CAUSE.PRODUCT_NOT_FOUND;

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

    void update(ProductDto productDto){

        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new InvalidProductException(PRODUCT_NOT_FOUND));

        product.setName(productDto.getName() == null ? product.getName() : productDto.getName());
        product.setPrice(productDto.getPrice() == null ? product.getPrice() : productDto.getPrice());
        product.setAmount(productDto.getAmount() == 0 ? product.getAmount() : product.getAmount()-productDto.getAmount());

        productRepository.save(product);
    }

}
