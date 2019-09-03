package com.makeup.order.domain;

import com.makeup.order.domain.dto.CreateOrderDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.transaction.Transactional;

@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OrderFacade {

    OrderService orderService;

    public void addOrder(CreateOrderDto createOrderDto){
        orderService.addOrder(createOrderDto);
    }
}
