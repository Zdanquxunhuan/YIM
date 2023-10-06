package com.yuge.yimcommon.exception;

import com.yuge.yimcommon.enums.StatusEnum;

public class YIMException extends GenericException {

    public YIMException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public YIMException(Exception e, String errorCode, String errorMessage) {
        super(e, errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public YIMException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public YIMException(StatusEnum statusEnum) {
        super(statusEnum.getMessage());
        this.errorMessage = statusEnum.message();
        this.errorCode = statusEnum.code();
    }

    public YIMException(StatusEnum statusEnum, String message) {
        super(message);
        this.errorMessage = message;
        this.errorCode = statusEnum.getCode();
    }

    public YIMException(Exception oriEx) {
        super(oriEx);
    }

    public YIMException(Throwable oriEx) {
        super(oriEx);
    }

    public YIMException(String message, Exception oriEx) {
        super(message, oriEx);
        this.errorMessage = message;
    }

    public YIMException(String message, Throwable oriEx) {
        super(message, oriEx);
        this.errorMessage = message;
    }


}
