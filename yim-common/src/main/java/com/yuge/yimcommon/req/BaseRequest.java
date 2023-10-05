package com.yuge.yimcommon.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BaseRequest {

    @ApiModelProperty(required = false, value = "唯一请求号", example = "123456789")
    private String reqNo;

    @ApiModelProperty(required = false, value = "当前时间戳", example = "0")
    private int timestamp;

    public BaseRequest() {
        this.setTimestamp((int) (System.currentTimeMillis() / 1000));
    }

}
