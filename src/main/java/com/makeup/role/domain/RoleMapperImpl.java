package com.makeup.role.domain;

import com.makeup.role.domain.dto.RoleDto;

import java.util.HashSet;
import java.util.Set;

class RoleMapperImpl implements RoleMapper {
    @Override
    public RoleDto toDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setRole(role.getRole());
        return roleDto;
    }

    @Override
    public Set<RoleDto> toDtoSet(Set<Role> roles) {
        Set<RoleDto> set = new HashSet<>(roles.size());
        roles.forEach(role -> set.add(toDto(role)));

        return set;
    }
}
