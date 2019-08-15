package com.makeup.user.domain;

import com.makeup.role.domain.query.RoleQueryDto;
import com.makeup.user.domain.exceptions.PasswordConstraintException;
import com.makeup.utils.GlobalAuthorization;
import com.makeup.user.domain.dto.CreateUserDto;
import com.makeup.user.domain.exceptions.InvalidUserException;

import com.makeup.utils.ParameterizedException;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;

import javax.validation.Valid;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.makeup.user.domain.exceptions.InvalidUserException.CAUSE.*;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
class UserValidator {
    private static final String USER_LOGGED_SUCCESSFULLY = "User: %s logged.";
    UserRepository userRepository;
//    UserMapper userMapper;

    boolean checkInputDto(CreateUserDto createUserDto){
        String login = createUserDto.getLogin();

        checkValueIfBlank(login, new InvalidUserException(LOGIN_BLANK));
        checkLoginLength(login);
        userRepository.findByLogin(login).ifPresent(user -> {
            log.error(LOGIN_EXISTS.getMessage());
            throw new InvalidUserException(LOGIN_EXISTS);
        });

        validateEmail(createUserDto.getEmail());
//        String hashedPassword = isValidPassword(createUserDto);
//        createUserDto.setPassword(hashedPassword);
        return isValidPassword(createUserDto);
    }

    void authorizeUser(String login, String password){
        userRepository.findByLogin(login)
                .filter(user -> BCrypt.checkpw(password, user.getPassword()))
                .ifPresentOrElse(user -> {
                            GlobalAuthorization.name = user.getLogin();
                            GlobalAuthorization.assignRolesToAuthorizationUser(getRoleNameFromUser(user));
                            log.info(String.format(USER_LOGGED_SUCCESSFULLY, user.getLogin()));
                        },
                        () -> {
                            log.info(String.format(FAILED_LOGIN_ATTEMPT.getMessage(), login));
                            throwException(new InvalidUserException(CORRECT_LOGIN_OR_PASSWORD));
                        });
    }

    boolean isValidPassword(@Valid CreateUserDto createUserDto) {
        BeanValidationBinder<CreateUserDto> binder = new BeanValidationBinder<>(CreateUserDto.class);
        TextField passwordField = new TextField();
        passwordField.setValue(createUserDto.getPassword());
        binder.forField(passwordField).bind("password");

        binder.setBean(createUserDto);

        if (!binder.isValid()){
            throw new PasswordConstraintException(ParameterizedException.exception);
        }
        createUserDto.setPassword(getHashedPassword(createUserDto.getPassword()));
        return binder.isValid();

//        return BCrypt.hashpw(passwordField.getValue(), BCrypt.gensalt(10));
    }

    private String getHashedPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    private void validateEmail(String email){
        checkValueIfBlank(email, new InvalidUserException(EMAIL_BLANK));
        checkEmailByRegExp(email);
        userRepository.findByEmail(email).ifPresent(user -> {
            log.error(EMAIL_EXISTS.getMessage());
            throw new InvalidUserException(EMAIL_EXISTS);
        });
    }

    private void checkValueIfBlank(String value, RuntimeException exception){
        if (StringUtils.isBlank(value))
            throwException(exception);
    }

    private void checkLoginLength(String value){
        if (value.length() < 5){
            throwException(new InvalidUserException(LOGIN_MIN_LENGTH));
        } else if (value.length() > 15)
            throwException(new InvalidUserException(LOGIN_MAX_LENGTH));
    }

    private void checkEmailByRegExp(String email){
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+[a-zA-Z0-9.]+@[a-zA-Z0-9]+.+[a-zA-Z0-9.]+[a-zA-Z0-9]+", Pattern.CASE_INSENSITIVE);

       if (!pattern.matcher(email).matches()){
            log.error(EMAIL_INCORRECT.getMessage());
            throw new InvalidUserException(EMAIL_INCORRECT);
        }
    }

    private Set<String> getRoleNameFromUser(User user){
        return user.getRoles().stream()
                .map(RoleQueryDto::getRole)
                .collect(Collectors.toSet());
    }

    private void throwException(RuntimeException exception){
        throw exception;
    }
}