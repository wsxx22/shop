package com.makeup.utils;

public abstract class ParameterizedException extends RuntimeException {
    public static String exception;

    public ParameterizedException(String message) {
        super(message);
        exception = message;
    }
}
