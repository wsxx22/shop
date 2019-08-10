package com.makeup.shared;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class User {
        public static final String USER_ROLE = "USER";
        public static final String ADMIN_ROLE = "ADMIN";
    }
}
