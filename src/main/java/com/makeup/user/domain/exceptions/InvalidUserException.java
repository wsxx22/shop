package com.makeup.user.domain.exceptions;

import com.makeup.utils.ParameterizedException;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class InvalidUserException extends ParameterizedException {

    @Getter
//    @AllArgsConstructor
    public enum CAUSE {
        USER_NOT_FOUND("User not found"),
        LOGIN_BLANK("Login cannot be blank"),
        LOGIN_LENGTH("Login must have a minimum 5 characters"),
        LOGIN_EXISTS("Login exists. Change it"),
        EMAIL_INCORRECT("Correct mail"),
        EMAIL_EXISTS("Email exists. Change it");

        String message;

        CAUSE(String message) {
            this.message = message;
        }
    }

    public InvalidUserException(CAUSE cause) {
        super(cause.message);
    }
}