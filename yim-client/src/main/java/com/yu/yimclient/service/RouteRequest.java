package com.yu.yimclient.service;

import com.yu.yimclient.dto.LoginReqDTO;
import com.yu.yimclient.dto.YIMServerResDTO;

public interface RouteRequest {

    YIMServerResDTO.ServerInfo loginAndGetYIMServer(LoginReqDTO loginReqDTO);
}
