package com.makeup.user.domain;

import com.makeup.role.domain.RoleFacade;
import com.makeup.role.domain.dto.CreateRoleDto;
import com.makeup.role.domain.exception.InvalidRoleException;
import com.makeup.role.domain.query.RoleQueryDto;
import com.makeup.user.domain.dto.CreateUserDto;
import com.makeup.user.domain.query.UserQueryDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
class UserFactory {
    private static final String ACCOUNT_CREATED = "Account created. Username: %s, email: %s";
    final RoleFacade roleFacade;

    @Getter(AccessLevel.PACKAGE)
    Set<CreateRoleDto> roleDtoSetSpockTesting;

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
        CreateRoleDto createRoleDto;
        if (roleFacade != null) {
            createRoleDto = roleFacade.findRole(roleName);
        }else {
            roleDtoSetSpockTesting = new HashSet<>();
            long size = roleDtoSetSpockTesting.size()+1L;
            createRoleDto = new CreateRoleDto(size,roleName);
            roleDtoSetSpockTesting.add(createRoleDto);
        }
        return new RoleQueryDto(createRoleDto.getId(), createRoleDto.getRole());
    }




}