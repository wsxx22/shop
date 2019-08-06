package com.makeup.user.domain;

import com.makeup.user.domain.dto.CreateUserDto;
import com.makeup.user.domain.dto.UserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
//@AllArgsConstructor
class UserService {

    public UserService(UserMapper userMapper, UserRepository userRepository, UserFactory userFactory, UserValidator userValidator) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.userFactory = userFactory;
        this.userValidator = userValidator;
    }

    UserMapper userMapper;
    UserRepository userRepository;
    UserFactory userFactory;
    UserValidator userValidator;

    void create (CreateUserDto createUserDto, String role) {
        userValidator.checkInputDto(createUserDto);
        userRepository.save(userFactory.create(createUserDto, role));
    }


}
