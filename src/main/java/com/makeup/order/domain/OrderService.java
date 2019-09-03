package com.makeup.order.domain;

import com.makeup.order.domain.dto.CreateOrderDto;
import com.makeup.product.domain.ProductFacade;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
class OrderService {

    OrderRepository orderRepository;
    OrderMapperImpl orderMapper;
    ProductFacade productFacade;

    void addOrder(CreateOrderDto createOrderDto){

        orderRepository.save(orderMapper.toEntity(createOrderDto));
        createOrderDto.getProducts().forEach(productDto -> productFacade.update(productDto));
    }

}
