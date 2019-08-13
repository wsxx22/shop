package com.makeup.user.domain;

import com.makeup.role.domain.RoleFacade;
import com.makeup.role.domain.dto.CreateRoleDto;
import com.makeup.role.domain.query.RoleQueryDto;
import com.makeup.user.domain.dto.CreateUserDto;
import com.makeup.user.domain.query.UserQueryDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
class UserFactory {
    private static final String ACCOUNT_CREATED = "Account created. Username: %s, email: %s";
    RoleFacade roleFacade;

    UserQueryDto create(CreateUserDto createUserDto, String roleName){
        UserQueryDto user = new UserQueryDto(createUserDto.getLogin(), createUserDto.getPassword(), createUserDto.getEmail());
        assignUserToRole(user, roleName);
        log.info(String.format(ACCOUNT_CREATED, createUserDto.getLogin(), createUserDto.getEmail()));
        return user;
    }

    private void assignUserToRole(UserQueryDto user, String roleName) {
        RoleQueryDto role = getQueryRoleDto(roleName);
        role.addUser(user);
        user.addRole(role);
    }

    private RoleQueryDto getQueryRoleDto(String roleName){
        CreateRoleDto createRoleDto = roleFacade.findRole(roleName);
        return new RoleQueryDto(createRoleDto.getId(), createRoleDto.getRole());
    }
}