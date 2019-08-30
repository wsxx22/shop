package com.makeup.product.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProductConfiguration {

    @Bean
    ProductFacade productFacade(ProductRepository productRepository){
        return new ProductFacade(new ProductService(
                productRepository,
                new ProductFactory(new CategoryMapperImpl()),
                new ProductValidator(productRepository),
                new ProductMapperImpl()
        ));
    }
}
