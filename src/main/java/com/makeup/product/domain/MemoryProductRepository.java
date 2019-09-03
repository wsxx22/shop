package com.makeup.product.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
class MemoryProductRepository implements ProductRepository {

    ConcurrentHashMap<Long, Product> map;

    @Override
    public Optional<Product> findByName(String name) {
        return map.entrySet().stream()
                .filter(entrySet -> entrySet.getValue().getName().equals(name))
                .findAny()
                .map(Map.Entry::getValue);
    }

    @Override
    public void save(Product product) {
        map.put(product.getId(), product);
    }

    @Override
    public Set<Product> findAll() {
       return new HashSet<>(map.values());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }
}
