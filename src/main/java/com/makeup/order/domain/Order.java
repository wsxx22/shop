package com.makeup.order.domain;

import com.makeup.product.domain.query.ProductQueryDto;
import com.makeup.user.domain.query.UserQueryDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Builder
@Getter
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

    @Getter(AccessLevel.NONE)
    @ManyToMany(fetch = FetchType.EAGER)
            @JoinTable(name = "order_products", joinColumns = @JoinColumn(name = "id_order"),
                                                inverseJoinColumns = @JoinColumn(name = "id_product"))
    Set<ProductQueryDto> products;

    Set<ProductQueryDto> getProducts(){
        return new HashSet<>(products);
    }
}
