package com.makeup.role.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.Set;

interface RoleRepository extends Repository<Role, Long> {
    Optional<Role> findByRole (String role);
    Set<Role> findAll();
}
