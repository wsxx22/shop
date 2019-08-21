package com.makeup.product.domain;

import org.springframework.data.repository.Repository;

import java.util.Set;

interface CategoryRepository extends Repository<Category, Long> {

    Set<Category> findAll();
}
