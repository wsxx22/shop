package com.makeup.product.domain.exception;

import com.makeup.utils.ParameterizedException;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class InvalidCategoryException extends ParameterizedException {

    @Getter
    @AllArgsConstructor
    public enum CAUSE{
        CANT_CONVERT_TO_DTO("Category could not converted to Dto."),
        DTO_SET_IS_EMPTY("Categories set is empty.");

        String message;
    }

    public InvalidCategoryException(CAUSE cause) {
        super(cause.message);
    }
}
