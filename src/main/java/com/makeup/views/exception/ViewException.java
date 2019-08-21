package com.makeup.views.exception;

import com.makeup.utils.ParameterizedException;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class ViewException extends ParameterizedException {

    @Getter
    @AllArgsConstructor
    public enum CAUSE {
        FIELD_COULD_NOT_BE_BLANK("%s could not be blank"),
        FIELD_MUST_BE_DIGIT("%s must be digit. Format xxxx.xx"),
        FIELD_MUST_BE_INTEGER("%s must be digit. Range 0 - 1000");

        String message;
    }

    public ViewException(CAUSE cause) {
        super(cause.message);
    }
}
