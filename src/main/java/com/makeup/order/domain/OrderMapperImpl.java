package com.makeup.order.domain;

import com.makeup.order.domain.dto.CreateOrderDto;
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
