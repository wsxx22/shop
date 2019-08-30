package com.makeup.product.domain.exception;

import com.makeup.utils.ParameterizedException;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class InvalidProductException extends ParameterizedException {

    @Getter
    @AllArgsConstructor
    public enum CAUSE{
        CANT_CONVERT_TO_DTO("Product could not converted to Dto."),
        INVALID_PRODUCT_NAME("Product must contains from 1 to 30 characters."),
        INVALID_PRODUCT_DESCRIPTION("Product description can contain maximum 200 characters."),
        INVALID_PRODUCT_PRICE("Product must contain the price. Maximum price is 10.000"),
        INVALID_PRODUCT_CAPACITY("Product must contain the capacity. Maximum capacity is 10 liters"),
        INVALID_PRODUCT_AMOUNT("Value range 0-1000"),
        PRODUCT_NAME_EXISTS("Product exists.");

        String message;
    }

    public InvalidProductException(CAUSE cause) {
        super(cause.message);
    }
}
