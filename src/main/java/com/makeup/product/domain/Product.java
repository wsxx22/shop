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
    Long id;
    String name;
    String description;
    double amount;
    BigDecimal price;

    @Getter(AccessLevel.NONE)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
            @JoinTable(name = "product_categories", joinColumns = @JoinColumn(name = "id_product"),
                                                    inverseJoinColumns = @JoinColumn(name = "id_category"))
    Set<Category> categories;
}
