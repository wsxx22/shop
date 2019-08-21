package com.makeup.product.domain;

import org.springframework.data.repository.Repository;

import java.util.Set;

interface ProductRepository extends Repository<Product, Long> {

    void save (Product product);

    Set<Product> findAll();

}
