package com.makeup.order.domain;

import com.makeup.order.domain.dto.CreateOrderDto;
import com.makeup.order.domain.dto.OrderDto;
import com.makeup.product.domain.ProductFacade;
import com.makeup.utils.GlobalAuthorization;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
class OrderService {

    OrderRepository orderRepository;
    OrderMapperImpl orderMapper;
    ProductFacade productFacade;

    void addOrder(CreateOrderDto createOrderDto){
        orderRepository.save(orderMapper.toEntity(createOrderDto));
        createOrderDto.getProducts().forEach(productDto -> productFacade.buyProduct(productDto));
    }

    Set<OrderDto> findAllByLoggedUser(){
        return orderMapper.toOrdersDto(orderRepository.findAllByCustomer_Login(GlobalAuthorization.name));
    }

}
