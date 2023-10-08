package com.yu.route.service;

import com.yu.yimrouteapi.req.LoginReqDTO;
import com.yu.yimrouteapi.req.RegisterInfoReqDTO;
import com.yu.yimrouteapi.res.RegisterInfoResDTO;
import com.yuge.yimcommon.enums.StatusEnum;

public interface AccountService {


    RegisterInfoResDTO register(RegisterInfoResDTO registerInfo);

    StatusEnum login(LoginReqDTO loginReq);

    void saveRouteInfo(Long userId, String serverInfo);
}
