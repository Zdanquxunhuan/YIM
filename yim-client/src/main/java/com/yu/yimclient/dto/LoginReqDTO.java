package com.yu.yimclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class LoginReqDTO {
    private Long userId;
    private String userName;
}
