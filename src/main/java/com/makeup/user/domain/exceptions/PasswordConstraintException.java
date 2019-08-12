package com.makeup.user.domain.exceptions;

import com.makeup.utils.ParameterizedException;
import lombok.AllArgsConstructor;

public class PasswordConstraintException extends ParameterizedException {

    @AllArgsConstructor
    public enum CAUSE {
        PASSWORD_IS_BLANK("Password cannot be blank"),
        REPEATED_PASSWORD_NOT_THE_SAME("Repeat correct password.");

       String message;
    }

    public PasswordConstraintException(CAUSE cause) {
        super(cause.message);
    }

    public PasswordConstraintException(String message) {
        super(message);
    }
}
