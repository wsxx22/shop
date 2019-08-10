package com.makeup.user.domain;

import com.makeup.role.domain.Role;
import com.makeup.role.domain.RoleFacade;
import com.makeup.role.domain.dto.RoleDto;
import com.makeup.user.domain.dto.CreateUserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
class UserFactory {
    RoleFacade roleFacade;

    User create(CreateUserDto createUserDto, String role){
        return User.builder()
                .login(createUserDto.getLogin())
                .password(createUserDto.getPassword())
                .email(createUserDto.getEmail())
                .roles(Set.of(getRole(role)))
                .build();
    }

    private Role getRole (String roleName){
        RoleDto roleDto = roleFacade.findRole(roleName);
        return new Role(roleDto.getId(), roleDto.getRole());
    }
}