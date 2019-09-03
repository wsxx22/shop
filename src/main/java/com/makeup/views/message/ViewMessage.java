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
        PRODUCT_ADDED("Product added."),
        AMOUNT_RANGE("Amount range must be between 1 and %s."),
        PRODUCT_BOUGHT("Product %s bought.");

        String message;
    }

    public ViewMessage(String message) {
        super(message);
    }
}
