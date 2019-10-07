package com.makeup.role.domain;

import com.makeup.role.domain.dto.CreateRoleDto;
import com.makeup.role.domain.exception.InvalidRoleException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Optional;
import java.util.Set;

import static com.makeup.role.domain.exception.InvalidRoleException.CAUSE.ROLE_EXISTS;
import static com.makeup.role.domain.exception.InvalidRoleException.CAUSE.ROLE_NOT_FOUND;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
class RoleService {
    RoleRepository roleRepository;
    RoleMapperImpl roleMapper;

    CreateRoleDto findRole(String role) {
        return roleMapper.toDto(roleRepository.findByRole(role)
                .orElseThrow(() -> new InvalidRoleException(ROLE_NOT_FOUND)));
    }

    Set<CreateRoleDto> findRoles() {
        return roleMapper.toDtoSet(roleRepository.findAll());
    }

    void addRole(CreateRoleDto createRoleDto) {
        roleRepository.findByRole(createRoleDto.getRole())
                .ifPresent(role -> {
                    throw new InvalidRoleException(ROLE_EXISTS);
                });

        roleRepository.save(roleMapper.roleToQueryDto(createRoleDto));
    }
}
