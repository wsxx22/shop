package com.makeup.order.domain;

import org.springframework.data.repository.Repository;

interface OrderRepository extends Repository<Order, Long> {
    void save(Order order);

}
