package com.makeup.user.domain;

import com.makeup.user.domain.dto.CreateUserDto;
import com.makeup.user.domain.query.UserQueryDto;
import com.makeup.utils.GlobalAuthorization;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
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

    void changePassword(String password){
        Optional<User> userQueryDto = userRepository.findByLogin(GlobalAuthorization.name);
        userQueryDto.ifPresent(u -> {
            u.changePassword(password);
//            log.info();
        });
    }

    void login(String name, String password){
        userValidator.authorizeUser(name, password);
    }
}