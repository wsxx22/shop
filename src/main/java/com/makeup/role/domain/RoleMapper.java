package com.makeup.role.domain;

import com.makeup.role.domain.dto.RoleDto;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
interface RoleMapper{
    RoleDto toDto (Role role);
    Set<RoleDto> toDtoSet(Set<Role> roles);
}