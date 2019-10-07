package com.makeup.role.domain;

import com.makeup.role.domain.query.RoleQueryDto;
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

    @Override
    public void save(RoleQueryDto roleQueryDto) {
        Role role = Role.builder()
                .id(roleQueryDto.getId())
                .role(roleQueryDto.getRole()).build();
        arrayDB.add(role);
    }


}
