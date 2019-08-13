package com.makeup.role.domain.exception;

import com.makeup.utils.ParameterizedException;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class InvalidRoleException extends ParameterizedException {

    @Getter
    @AllArgsConstructor
    public enum CAUSE{
        ROLE_NOT_FOUND("Role not found."),
        ROLE_COULD_NOT_CONVERTED("Could not converted");

        String message;
    }

    public InvalidRoleException(CAUSE cause) {
        super(cause.message);
    }
}