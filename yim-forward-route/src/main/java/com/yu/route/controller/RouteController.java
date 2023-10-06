package com.yu.route.controller;

import com.yu.route.cache.ServerCache;
import com.yu.route.dto.RouteInfo;
import com.yu.route.dto.YIMServerResDTO;
import com.yu.route.util.CheckServerUtil;
import com.yu.route.util.RouteInfoParseUtil;
import com.yu.yimrouteapi.RouteApi;
import com.yu.yimrouteapi.req.LoginReqDTO;
import com.yuge.yimcommon.res.BaseResponse;
import com.yuge.yimcommon.route.algorithm.RouteHandle;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RouteController implements RouteApi {

    @Autowired
    private RouteHandle routeHandle;

    @Autowired
    private ServerCache serverCache;

    @Autowired
    private CheckServerUtil checkServerUtil;

    @ApiOperation("Log in and get the server")
    @PostMapping("/loginAndGetServer")
    @Override
    public BaseResponse<YIMServerResDTO> loginAndGetServer(LoginReqDTO dto) {
        BaseResponse<YIMServerResDTO> res = new BaseResponse<>();

        // 127.0.0.1:11211:8081
        String server = routeHandle.routeServer(serverCache.getAllServerList(), String.valueOf(dto.getUserId()));
        log.info("[{}] route server info ===> {}",dto.getUserName(),server);

        RouteInfo routeInfo = RouteInfoParseUtil.parse(server);

        checkServerUtil.isAvailable(routeInfo);



        return null;
    }
}
