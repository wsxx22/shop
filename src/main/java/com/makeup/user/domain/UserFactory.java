package com.makeup.user.domain;

import com.makeup.role.domain.Role;
import com.makeup.role.domain.RoleFacade;
import com.makeup.role.domain.dto.RoleDto;
import com.makeup.user.domain.dto.CreateUserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
class UserFactory {
    RoleFacade roleFacade;

    User create(CreateUserDto createUserDto, String roleName){
        User user = new User(createUserDto.getLogin(), createUserDto.getPassword(), createUserDto.getEmail());
        assignUserToRole(user, roleName);
        return user;
    }

    private Role getRole (String roleName){
        RoleDto roleDto = roleFacade.findRole(roleName);
        return new Role(roleDto.getId(), roleDto.getRole());
    }

    private void assignUserToRole(User user, String roleName) {
        Role role = getRole(roleName);
        role.addUser(user);
        user.addRole(role);
    }
}