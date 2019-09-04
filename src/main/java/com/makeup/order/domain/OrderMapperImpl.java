package com.makeup.order.domain;

import com.makeup.order.domain.dto.CreateOrderDto;
import com.makeup.order.domain.dto.OrderDto;
import com.makeup.product.domain.dto.ProductDto;
import com.makeup.product.domain.query.ProductQueryDto;
import com.makeup.user.domain.UserFacade;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
class OrderMapperImpl implements OrderMapper {

    UserFacade userFacade;

    @Override
    public Order toEntity(CreateOrderDto createOrderDto) {
        return Order.builder()
                .customer(userFacade.findUserQueryByLogin(createOrderDto.getCustomer()))
                .orderDate(LocalDateTime.now())
                .price(getTotalPrice(createOrderDto.getProducts()))
                .products(getProductsQuery(createOrderDto.getProducts())).build();
    }

    @Override
    public OrderDto toDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .idCustomer(order.getCustomer().getId())
                .orderTime(order.getOrderDate())
                .totalPrice(order.getPrice())
                .products(getProductsDto(order.getProducts())).build();
    }

    @Override
    public Set<OrderDto> toOrdersDto(Set<Order> orders) {
        Set<OrderDto> ordersDto = new HashSet<>();
        orders.forEach(order -> ordersDto.add(toDto(order)));
        return ordersDto;
    }

    private Set<ProductDto> getProductsDto(Set<ProductQueryDto> productsQueryDto){
        Set<ProductDto> productsDto = new HashSet<>();
        productsQueryDto.forEach(productQueryDto -> productsDto.add(ProductDto.builder()
                                                                        .id(productQueryDto.getId())
                                                                        .name(productQueryDto.getName())
                                                                        .description(productQueryDto.getDescription())
                                                                        .capacity(productQueryDto.getCapacity())
                                                                        .amount(productQueryDto.getAmount()).build()
                                                                        ));
        return productsDto;
    }

    private BigDecimal getTotalPrice(Set<ProductDto> products){
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ProductDto dto : products){
            int amount = dto.getAmount();
            totalPrice = totalPrice.add(dto.getPrice().multiply(new BigDecimal(amount)));
        }
        return totalPrice;
    }

    private Set<ProductQueryDto> getProductsQuery(Set<ProductDto> productsDto){
        Set<ProductQueryDto> productsQuery = new HashSet<>();
        productsDto.forEach(productDto -> productsQuery.add(ProductQueryDto.builder()
                                                            .id(productDto.getId())
                                                            .name(productDto.getName())
                                                            .description(productDto.getDescription())
                                                            .capacity(productDto.getCapacity())
                                                            .price(productDto.getPrice())
                                                            .amount(productDto.getAmount()).build()));
        return productsQuery;
    }
}
