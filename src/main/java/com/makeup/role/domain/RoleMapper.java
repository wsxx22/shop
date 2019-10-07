package com.makeup.role.domain;

import com.makeup.role.domain.dto.CreateRoleDto;
import com.makeup.role.domain.query.RoleQueryDto;

import java.util.Set;

interface RoleMapper{
    CreateRoleDto toDto(Role role);
    Set<CreateRoleDto> toDtoSet(Set<Role> roles);
    RoleQueryDto roleToQueryDto(CreateRoleDto createRoleDto);
}