package com.makeup.user.domain.exceptions;

import com.makeup.utils.ParameterizedException;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class InvalidUserRoleException extends ParameterizedException {

    @Getter
//    @AllArgsConstructor
    public enum CAUSE{
        ROLE_NOT_FOUND("Role not found");

        String message;

        CAUSE(String message) {
            this.message = message;
        }
    }

    public InvalidUserRoleException(CAUSE cause) {
        super(cause.message);
    }
}
