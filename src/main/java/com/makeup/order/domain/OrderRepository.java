package com.makeup.order.domain;

import org.springframework.data.repository.Repository;

import java.util.Set;

interface OrderRepository extends Repository<Order, Long> {
    void save(Order order);
    Set<Order> findAllByCustomer_Login(String username);
}
