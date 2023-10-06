package com.yu.yimrouteapi.req;

import com.yuge.yimcommon.req.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class LoginReqDTO extends BaseRequest {
    private Long userId;
    private String userName;
}
