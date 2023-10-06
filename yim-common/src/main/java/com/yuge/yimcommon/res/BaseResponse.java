package com.yuge.yimcommon.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> implements Serializable {

    private String code;
    private String message;
    private String reqNo; //request number
    private T dataBody;

    public BaseResponse(T dataBody) {
        this.dataBody = dataBody;
    }

    public BaseResponse(String code, String message, T dataBody) {
        this.code = code;
        this.message = message;
        this.dataBody = dataBody;
    }


}
