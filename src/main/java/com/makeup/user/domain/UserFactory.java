package com.makeup.user.domain;

import com.makeup.user.domain.dto.CreateUserDto;
import com.makeup.user.domain.exceptions.InvalidUserRoleException.CAUSE;
import com.makeup.user.domain.exceptions.InvalidUserRoleException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Collections;

@FieldDefaults(level = AccessLevel.PRIVATE)
//@AllArgsConstructor
class UserFactory {
    UserRoleRepository userRoleRepository;
//    UserMapper userMapper;


    public UserFactory(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    User create(CreateUserDto createUserDto, String role) {
        return new User(
                createUserDto.getLogin(),
                createUserDto.getPassword(),
                createUserDto.getEmail(),
                Collections.singleton(getRole(role)));
//       return User.builder()
//                .login(createUserDto.getLogin())
//                .password(createUserDto.getPassword())
//                .email(createUserDto.getEmail())
//                .userRoles(Collections.singleton(getRole(role)))
//                .build();
    }

    private UserRole getRole (String role) {
        return userRoleRepository.findByRole(role).orElseThrow(() -> new InvalidUserRoleException(CAUSE.ROLE_NOT_FOUND));
    }
}