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
        FIELD_MUST_BE_INTEGER("%s must be digit. Range 0 - 1000"),
        PRODUCT_ADDED("Product added.");

        String message;
    }

    public ViewMessage(String message) {
        super(message);
    }
}
