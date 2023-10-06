package com.yuge.yimcommon.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class GenericException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;
    String errorCode;
    String errorMessage;

    public GenericException(String message) {
        super(message);
    }

    public GenericException(Exception oriEx) {
        super(oriEx);
    }

    public GenericException(Exception oriEx, String message) {
        super(message, oriEx);
    }

    public GenericException(Throwable oriEx) {
        super(oriEx);
    }

    public GenericException(String message, Exception oriEx) {
        super(message, oriEx);
    }

    public GenericException(String message, Throwable oriEx) {
        super(message, oriEx);
    }
}
