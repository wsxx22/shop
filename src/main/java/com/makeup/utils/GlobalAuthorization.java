package com.makeup.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

public final class GlobalAuthorization {

    public static String name;

    private static Set<String> userRoles;

    public static boolean isAuthorized(){
        return StringUtils.isNotBlank(name);
    }

    public static void assignRolesToAuthorizationUser(Set<String> roles) {
        userRoles = roles;
    }

    public static Set<String> getUserRoles() {
        return new HashSet<>(userRoles);
    }

    public static void removeUserRoles(){
        userRoles.clear();
    }

}
