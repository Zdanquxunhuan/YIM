package com.yu.yimrouteapi;

import com.yu.yimrouteapi.req.LoginReqDTO;

public interface RouteApi {

    Object loginAndGetServer(LoginReqDTO dto);
}
