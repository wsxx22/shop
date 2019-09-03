package com.makeup.user.domain;

import com.makeup.user.domain.dto.CreateUserDto;
import com.makeup.user.domain.exceptions.InvalidUserException;
import com.makeup.user.domain.query.UserQueryDto;
import com.makeup.utils.GlobalAuthorization;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.makeup.user.domain.exceptions.InvalidUserException.CAUSE.USER_NOT_FOUND;

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

    void changeEmail(String email){
        String username = GlobalAuthorization.name;
        userRepository.findByLogin(username).ifPresent(u -> {
            CreateUserDto createUserDto = CreateUserDto.builder().email(email).build();
            userValidator.validateEmail(createUserDto.getEmail());
                u.changeEmail(createUserDto.getEmail());
                log.info(username + " changed email. ");

        });
    }

    void login(String name, String password){
        userValidator.authorizeUser(name, password);
    }

    UserQueryDto findUserQueryByLogin(String login){
        User user = userRepository.findByLogin(login).orElseThrow(() -> new InvalidUserException(USER_NOT_FOUND));
        return UserQueryDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .email(user.getEmail())
                .roles(user.getRoles()).build();
    }


}