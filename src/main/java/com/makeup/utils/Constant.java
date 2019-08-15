package com.makeup.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class User {
        public static final String USER_ROLE = "USER";
        public static final String ADMIN_ROLE = "ADMIN";
        public static final String CASHIER_ROLE = "CASHIER";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Label {
        public static final String STORE_NAME = "MakeUp Shop";
        public static final String WELCOME_USER =
                String.format("Hello %s! This is your profile! Have a nice shopping!", GlobalAuthorization.name);
    }
}
