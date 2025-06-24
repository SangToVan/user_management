package com.sangto.user_management.constant;

public class Endpoint {
    public static final class V1 {
        public static final String PREFIX = "/api/v1/";

        public static final class Auth {
            public static final String BASE = PREFIX + "auth/";
            public static final String LOGIN = BASE + "login";
            public static final String REGISTER = BASE + "register";
            public static final String CHANGE_PASSWORD = BASE + "change_password";
        }

        public static final class Admin {
            public static final String BASE = PREFIX + "admin/";
            public static final String USER = BASE + "users";
            public static final String USER_DETAIL = BASE + "users/{id}";
            public static final String USER_DELETE = BASE + "users/{id}/delete";
            public static final String USER_ROLE = BASE + "users/{id}/role";
        }

        public static final class User {
            public static final String BASE = PREFIX + "user/";
            public static final String DETAIL = BASE + "detail";
        }
    }
}
