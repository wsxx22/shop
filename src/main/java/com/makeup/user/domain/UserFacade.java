package com.makeup.user.domain;

import com.makeup.user.domain.dto.CreateUserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
//@AllArgsConstructor
public class UserFacade {
    UserService userService;

    public UserFacade(UserService userService) {
        this.userService = userService;
    }

    public void create (CreateUserDto createUserDto, String role) {
        userService.create(createUserDto, role);
    }
}
