package com.yu.yimrouteapi.req;

import com.yuge.yimcommon.req.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterInfoReqDTO extends BaseRequest {

    @ApiModelProperty(required = true, value = "userName", example = "zhangsan")
    @NotNull(message = "用户名不能为空")
    private String userName;
}
