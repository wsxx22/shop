package com.makeup.user.domain;

import com.makeup.user.domain.dto.CreateUserDto;
import com.makeup.utils.GlobalAuthorization;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

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
        String username = GlobalAuthorization.name;
        userRepository.findByLogin(username).ifPresent(u -> {
            CreateUserDto createUserDto = CreateUserDto.builder().password(password).build();
            if (userValidator.isValidPassword(createUserDto)){
                u.changePassword(createUserDto.getPassword());
                log.info(username + " changed password. ");
            }
        });
    }

    void login(String name, String password){
        userValidator.authorizeUser(name, password);
    }
}