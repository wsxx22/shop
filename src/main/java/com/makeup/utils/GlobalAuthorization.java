package com.makeup.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Set;

public final class GlobalAuthorization {

    public static String name;

    public static boolean isAuthorized(){
        return StringUtils.isNotBlank(name);
    }

}
