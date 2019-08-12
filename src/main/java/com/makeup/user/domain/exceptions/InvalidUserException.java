package com.makeup.user.domain.exceptions;

import com.makeup.utils.ParameterizedException;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class InvalidUserException extends ParameterizedException {

    @Getter
    @AllArgsConstructor
    public enum CAUSE {
        USER_NOT_FOUND("User not found"),
        LOGIN_BLANK("Login cannot be blank"),
        LOGIN_LENGTH("Login must have a minimum 5 characters"),
        LOGIN_EXISTS("Login exists. Change it"),
        EMAIL_INCORRECT("Invalid email. Correct it"),
        EMAIL_EXISTS("Email exists. Change it"),
        EMAIL_BLANK("Email cannot be blank"),
        CORRECT_LOGIN_OR_PASSWORD("Login or password is incorrect. Correct it");

        String message;
    }

    public InvalidUserException(CAUSE cause) {
        super(cause.message);
    }
}