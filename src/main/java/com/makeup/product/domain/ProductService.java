package com.makeup.product.domain;

import com.makeup.product.domain.dto.CreateProductDto;
import com.makeup.product.domain.dto.ProductDto;
import com.makeup.product.domain.exception.InvalidProductException;
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

    private final static String PRODUCT_SAVED = "Product added. %s";
    private final static String PRODUCT_UPDATED = "Product %s updated. New parameters %s";

    ProductRepository productRepository;
    ProductFactory productFactory;
    ProductValidator productValidator;
    ProductMapperImpl productMapper;

    void addProduct (CreateProductDto createProductDto){
        productValidator.validCreateDto(createProductDto);
        productRepository.save(productFactory.create(createProductDto));
        log.info(String.format(PRODUCT_SAVED, createProductDto.toString()));
    }

    Set<ProductDto> findAllProducts(){
        return productMapper.toDtoSet(productRepository.findAll());
    }

    void buyProduct(ProductDto productDto){
        Product product = findProductById(productDto.getId());
        setDataProduct(productDto, product);
        product.setAmount(productDto.getAmount() == 0 ? product.getAmount() : product.getAmount()-productDto.getAmount());

        productRepository.save(product);
    }

    void update(ProductDto productDto){
        Product product = findProductById(productDto.getId());
        productValidator.validDtoToUpdate(productDto);
        setDataProduct(productDto, product);
        product.setAmount(productDto.getAmount() == 0 ? product.getAmount() : productDto.getAmount());

        productRepository.save(product);
        log.info(String.format(PRODUCT_UPDATED, product.getName(), product.toString()));
    }

    private void setDataProduct(ProductDto productDto, Product product) {
        product.setName(productDto.getName().isBlank() ? product.getName() : productDto.getName());
        product.setDescription(productDto.getDescription().isBlank() ? product.getDescription() : productDto.getDescription());
        product.setCapacity(productDto.getCapacity() == 0.00 ? product.getCapacity() : productDto.getCapacity());
        product.setPrice(productDto.getPrice() == null ? product.getPrice() : productDto.getPrice());
    }

    private Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new InvalidProductException(PRODUCT_NOT_FOUND));
    }

}
