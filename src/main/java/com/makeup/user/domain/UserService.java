package com.makeup.user.domain;

import com.makeup.user.domain.dto.CreateUserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
class UserService {
//    UserMapper userMapper;
    UserRepository userRepository;
    UserFactory userFactory;
    UserValidator userValidator;

    boolean create (CreateUserDto createUserDto, String role){
        if (userValidator.checkInputDto(createUserDto)){
            userRepository.save(userFactory.create(createUserDto, role));
            return true;
        }
       return false;
    }

    void login(String name, String password){
        userValidator.authorizeUser(name, password);
    }
}