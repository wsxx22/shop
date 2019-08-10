package com.makeup.user.domain;

import com.makeup.role.domain.Role;
import com.makeup.user.domain.dto.CreateUserDto;
import com.makeup.user.domain.dto.UserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
class UserService {
//    UserMapper userMapper;
    UserRepository userRepository;
    UserFactory userFactory;
    UserValidator userValidator;

    void create (CreateUserDto createUserDto, String role){
        userValidator.checkInputDto(createUserDto);
        userRepository.save(userFactory.create(createUserDto, role));
//        System.out.println("test w User service");
//        userRepository.save(new User("test22", "Pozdro123@", "asd@wp.pl"));
    }

    UserDto login (String login, String password) {
        return null;
    }
}
