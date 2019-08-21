package com.makeup.product.domain.exception;

import com.makeup.utils.ParameterizedException;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class InvalidCategoryException extends ParameterizedException {

    @Getter
    @AllArgsConstructor
    public enum CAUSE{
        CANT_CONVERT_TO_ENTITY("CategoryDto could not converted to entity."),
        CANT_CONVERT_TO_DTO("Category could not converted to Dto."),
        SET_IS_EMPTY("Category set is empty."),
        DTO_SET_IS_EMPTY("CategoryDto set is empty.");

        String message;
    }

    public InvalidCategoryException(CAUSE cause) {
        super(cause.message);
    }
}
