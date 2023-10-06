package com.yuge.yimcommon.enums;

public enum StatusEnum {
    ARGUMENT_CHECK_FAIL("3000","invalid argument"),
    SERVER_NOT_AVAILABLE("7100","No yim server is available, please try again!")
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
