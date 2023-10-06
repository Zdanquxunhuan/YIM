package com.yu.yimclient.service.impl;

import com.alibaba.fastjson.JSON;
import com.yu.yimclient.dto.LoginReqDTO;
import com.yu.yimclient.dto.YIMServerResDTO;
import com.yu.yimclient.service.RouteRequest;
import com.yu.yimrouteapi.RouteApi;
import com.yuge.yimcommon.proxy.HttpInvokeProxy;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class RouteRequestImpl implements RouteRequest {

    @Value("${yim.route.url}")
    private String routeUrl;

    @Autowired
    private OkHttpClient okHttpClient;

    @Override
    public YIMServerResDTO.ServerInfo loginAndGetYIMServer(LoginReqDTO loginReqDTO) {

        RouteApi routeApi = new HttpInvokeProxy<>(RouteApi.class, routeUrl, okHttpClient).getInstance();
        com.yu.yimrouteapi.req.LoginReqDTO dto = new com.yu.yimrouteapi.req.LoginReqDTO(loginReqDTO.getUserId(), loginReqDTO.getUserName());

        Response response = null;
        YIMServerResDTO yimServerResDTO = null;
        //Retry after failure
        try {
            response = (Response) routeApi.loginAndGetServer(dto);
            String json = response.body().string();
            yimServerResDTO = JSON.parseObject(json, YIMServerResDTO.class);
        } catch (IOException e) {
            log.error("exception:{}",e);
        }finally {
            response.body().close();
        }
        return yimServerResDTO.getServerInfo();
    }
}
