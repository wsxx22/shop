package com.makeup.product.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
class ProductConfiguration {

    @Bean
    ProductFacade productFacade(ProductRepository productRepository){
        CategoryMapperImpl categoryMapper = new CategoryMapperImpl();
        return new ProductFacade(new ProductService(
                productRepository,
                new ProductFactory(categoryMapper),
                new ProductValidator(productRepository),
                new ProductMapperImpl(categoryMapper)
        ));
    }

    ProductFacade productFacade(ConcurrentHashMap<Long, Product> db){
        MemoryProductRepository memoryProductRepository = new MemoryProductRepository(db);
        CategoryMapperImpl categoryMapper = new CategoryMapperImpl();
        return new ProductFacade(new ProductService(
                memoryProductRepository,
                new ProductFactory(categoryMapper),
                new ProductValidator(memoryProductRepository),
                new ProductMapperImpl(categoryMapper)
        ));
    }

}
