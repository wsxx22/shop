package com.makeup.role.domain;

import com.makeup.role.domain.dto.CreateRoleDto;
import org.mapstruct.Mapper;

import java.util.Set;

abstract class RoleMapper{
    abstract CreateRoleDto toDto (Role role);
    abstract Set<CreateRoleDto> toDtoSet(Set<Role> roles);
}