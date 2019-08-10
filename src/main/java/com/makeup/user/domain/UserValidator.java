package com.makeup.user.domain;

import com.makeup.user.domain.dto.CreateUserDto;
import com.makeup.user.domain.exceptions.InvalidUserException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.makeup.user.domain.exceptions.InvalidUserException.CAUSE.*;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
class UserValidator {
    UserRepository userRepository;

    void checkInputDto(CreateUserDto createUserDto){
        String login = createUserDto.getLogin();

        checkValueIfBlank(login, new InvalidUserException(LOGIN_BLANK));
        checkLoginLength(login, new InvalidUserException(LOGIN_LENGTH));
        userRepository.findByLogin(login).ifPresent(user -> {
            log.error(LOGIN_EXISTS.getMessage());
            throw new InvalidUserException(LOGIN_EXISTS);
        });

        validateEmail(createUserDto.getEmail());
    }

    private void validateEmail(String email){
        checkValueIfBlank(email, new InvalidUserException(LOGIN_BLANK));
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

    private void checkLoginLength(String value, RuntimeException exception){
        if (value.length() < 5)
            throwException(exception);
    }

    private void checkEmailByRegExp(String email){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9.]+@[a-zA-Z0-9]+.+[a-zA-Z0-9.]+.{0}$", Pattern.CASE_INSENSITIVE);

       if (!pattern.matcher(email).matches()){
            log.error(EMAIL_INCORRECT.getMessage());
            throw new InvalidUserException(EMAIL_INCORRECT);
        }
    }

    private void throwException(RuntimeException exception){
        log.error(exception.getMessage());
        throw exception;
    }
}