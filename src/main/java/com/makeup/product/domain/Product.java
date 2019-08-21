package com.makeup.product.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Builder
@Table(name = "products")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    double capacity;
    BigDecimal price;
    int amount;

    @Getter(AccessLevel.NONE)
    @ManyToMany(fetch = FetchType.EAGER)
            @JoinTable(name = "product_categories", joinColumns = @JoinColumn(name = "id_product"),
                                                    inverseJoinColumns = @JoinColumn(name = "id_category"))
    Set<Category> categories;
}
