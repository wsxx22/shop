package com.makeup.views.message;

import com.makeup.utils.ParameterizedException;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class ViewMessage extends ParameterizedException {

    @Getter
    @AllArgsConstructor
    public enum CAUSE {
        COULD_NOT_BE_BLANK("%s could not be blank"),
        FIELD_MUST_BE_DIGIT("%s must be digit. Format x.xx"),
        FIELD_MUST_BE_INTEGER("%s must be digit."),
        PRODUCT_MUST_BE_SELECTED("Product must be selected if u want update."),
        PRODUCT_ADDED("Product added."),
        PRODUCT_UPDATED("Product %s updated."),
        AMOUNT_TO_BUY_RANGE("Amount range must be between 1 and %s."),
        PRODUCT_BOUGHT("Product %s bought."),
        LOG_IN("Log in if u want to buy something.");

        String message;
    }

    public ViewMessage(String message) {
        super(message);
    }
}
