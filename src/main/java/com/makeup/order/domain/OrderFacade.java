package com.makeup.order.domain;

import com.makeup.order.domain.dto.CreateOrderDto;
import com.makeup.order.domain.dto.OrderDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.transaction.Transactional;
import java.util.Set;

@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OrderFacade {

    OrderService orderService;

    public void addOrder(CreateOrderDto createOrderDto){
        orderService.addOrder(createOrderDto);
    }

    public Set<OrderDto> findAllByLoggedUser(){
        return orderService.findAllByLoggedUser();
    }
}
