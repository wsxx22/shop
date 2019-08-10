package com.makeup.user.domain;

import com.makeup.user.domain.dto.CreateUserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class UserFacade {
    UserService userService;

    public void create (CreateUserDto createUserDto, String role) {
        userService.create(createUserDto, role);
        System.out.println("po create , facade");
    }
}
