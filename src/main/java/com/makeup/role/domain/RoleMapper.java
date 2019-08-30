package com.makeup.role.domain;

import com.makeup.role.domain.dto.CreateRoleDto;
import java.util.Set;

interface RoleMapper{
    CreateRoleDto toDto(Role role);
    Set<CreateRoleDto> toDtoSet(Set<Role> roles);
}