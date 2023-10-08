package com.yu.yimrouteapi;

import com.yu.yimrouteapi.req.LoginReqDTO;
import com.yu.yimrouteapi.req.RegisterInfoReqDTO;
import com.yu.yimrouteapi.res.RegisterInfoResDTO;
import com.yuge.yimcommon.res.BaseResponse;

public interface RouteApi {

    Object loginAndGetServer(LoginReqDTO dto);

    BaseResponse<RegisterInfoResDTO> registerAccount(RegisterInfoReqDTO registerInfoReq);
}
