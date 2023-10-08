package com.yuge.yimcommon.enums;

public enum StatusEnum {
    SUCCESS("9000","success"),
    ARGUMENT_CHECK_FAIL("3000","invalid argument"),
    SERVER_NOT_AVAILABLE("server-login-400","No yim server is available, please try again!"),
    ACCOUNT_NOT_MATCH("client-login-400","The user information you have used is not correct"),
    REPEAT_LOGIN("5000","Repeat login, log out an account please!")
    ;

    StatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private final String code;
    private final String message;

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
