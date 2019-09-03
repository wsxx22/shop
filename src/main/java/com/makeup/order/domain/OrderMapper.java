package com.makeup.order.domain;

import com.makeup.order.domain.dto.CreateOrderDto;

interface OrderMapper {
    Order toEntity(CreateOrderDto createOrderDto);
}
