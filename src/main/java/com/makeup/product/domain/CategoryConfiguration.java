package com.makeup.product.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CategoryConfiguration {

    @Bean
    CategoryFacade categoryFacade(CategoryRepository categoryRepository){
        return new CategoryFacade(new CategoryService(
                categoryRepository, new CategoryMapperImpl()
        ));
    }
}
