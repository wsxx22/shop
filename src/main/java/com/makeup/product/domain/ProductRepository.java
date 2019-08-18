package com.makeup.product.domain;

import org.springframework.data.repository.Repository;

public interface ProductRepository extends Repository<Product, Long> {

    void save (Product product);

}
