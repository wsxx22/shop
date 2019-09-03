package com.makeup.order.domain;

import com.makeup.product.domain.ProductFacade;
import com.makeup.user.domain.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrderConfiguration {

    @Bean
    OrderFacade orderFacade(OrderRepository orderRepository, UserFacade userFacade, ProductFacade productFacade){
        return new OrderFacade(
                new OrderService(
                        orderRepository, new OrderMapperImpl(userFacade), productFacade));
    }
}
