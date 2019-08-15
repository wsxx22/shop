package com.makeup.role.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
class MemoryRoleRepository implements RoleRepository {

    Set<Role> arrayDB;

    @Override
    public Optional<Role> findByRole(String role) {
        return arrayDB.stream()
                .filter(r -> r.getRole().equals(role))
                .findFirst();
    }

    @Override
    public Set<Role> findAll() {
        return arrayDB;
    }
}
