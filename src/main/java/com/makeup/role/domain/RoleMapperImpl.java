package com.makeup.role.domain;

import com.makeup.role.domain.dto.CreateRoleDto;
import com.makeup.role.domain.exception.InvalidRoleException;
import com.makeup.role.domain.query.RoleQueryDto;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.makeup.role.domain.exception.InvalidRoleException.CAUSE.ROLE_COULD_NOT_CONVERTED;
import static com.makeup.role.domain.exception.InvalidRoleException.CAUSE.ROLE_NOT_FOUND;

@Slf4j
class RoleMapperImpl implements RoleMapper {
    @Override
    public CreateRoleDto toDto(Role roleEntity) {
        Role role = Optional.ofNullable(roleEntity).orElseThrow(() -> {
            log.error(String.format(ROLE_NOT_FOUND.getMessage(), ROLE_COULD_NOT_CONVERTED.getMessage()));
            throw new InvalidRoleException(ROLE_NOT_FOUND);
        });

        CreateRoleDto createRoleDto = new CreateRoleDto();
        createRoleDto.setId(role.getId());
        createRoleDto.setRole(role.getRole());
        return createRoleDto;
    }

    @Override
    public Set<CreateRoleDto> toDtoSet(Set<Role> roles) {
        Set<CreateRoleDto> set = new HashSet<>(roles.size());
        roles.forEach(role -> set.add(toDto(role)));

        return set;
    }

    @Override
    public RoleQueryDto roleToQueryDto(CreateRoleDto createRoleDto) {
        return new RoleQueryDto(createRoleDto.getId(), createRoleDto.getRole());
    }

}
