package com.makeup.product.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.Set;

interface ProductRepository extends Repository<Product, Long> {

    Optional<Product> findByName(String name);
    void save(Product product);
    Set<Product> findAll();

}
