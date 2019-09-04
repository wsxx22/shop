package com.makeup.order.domain;

import com.makeup.order.domain.dto.CreateOrderDto;
import com.makeup.order.domain.dto.OrderDto;

import java.util.Set;

interface OrderMapper {
    Order toEntity(CreateOrderDto createOrderDto);
    OrderDto toDto(Order order);
    Set<OrderDto> toOrdersDto(Set<Order> orders);
}
