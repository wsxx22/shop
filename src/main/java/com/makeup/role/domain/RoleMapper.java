package com.makeup.role.domain;

import com.makeup.role.domain.dto.CreateRoleDto;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
interface RoleMapper{
    CreateRoleDto toDto (Role role);
    Set<CreateRoleDto> toDtoSet(Set<Role> roles);
}