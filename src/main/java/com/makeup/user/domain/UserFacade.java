package com.makeup.user.domain;

import com.makeup.user.domain.dto.CreateUserDto;
import com.makeup.utils.GlobalAuthorization;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.transaction.Transactional;

@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class UserFacade {
    UserService userService;

    public boolean create (CreateUserDto createUserDto, String role) {
        return userService.create(createUserDto, role);
    }

    public void login (String username, String password) {
        userService.login(username, password);
    }

    public void logout(){
        GlobalAuthorization.name = null;
    }
}
