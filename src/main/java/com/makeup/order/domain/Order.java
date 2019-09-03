package com.makeup.order.domain;

import com.makeup.product.domain.query.ProductQueryDto;
import com.makeup.user.domain.query.UserQueryDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "orders")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
            @JoinColumn(name = "id_customer", referencedColumnName = "id")
    UserQueryDto customer;

    LocalDateTime orderDate = LocalDateTime.now();
    BigDecimal price;

    @ManyToMany(fetch = FetchType.EAGER)
            @JoinTable(name = "order_products", joinColumns = @JoinColumn(name = "id_order"),
                                                inverseJoinColumns = @JoinColumn(name = "id_product"))
    Set<ProductQueryDto> products;
}
