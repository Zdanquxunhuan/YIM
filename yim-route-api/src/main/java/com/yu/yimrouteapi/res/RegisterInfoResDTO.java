package com.yu.yimrouteapi.res;

import com.yuge.yimcommon.res.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterInfoResDTO extends BaseResponse {

    private Long userId ;
    private String userName ;

}
