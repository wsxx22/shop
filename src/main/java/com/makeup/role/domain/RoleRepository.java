package com.makeup.role.domain;

import com.makeup.role.domain.query.RoleQueryDto;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.Set;

interface RoleRepository extends Repository<Role, Long> {
    Optional<Role> findByRole (String role);
    Set<Role> findAll();
    void save(RoleQueryDto roleQueryDto);
}
