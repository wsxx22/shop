package com.makeup.product.domain.exception;

import com.makeup.utils.ParameterizedException;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class InvalidProductException extends ParameterizedException {

    @Getter
    @AllArgsConstructor
    public enum CAUSE{
        CANT_CONVERT_TO_DTO("Could not converted to Dto");

        String message;
    }

    public InvalidProductException(CAUSE cause) {
        super(cause.message);
    }
}
