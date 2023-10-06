package com.yu.route.util;

import com.yu.route.dto.RouteInfo;
import com.yuge.yimcommon.exception.YIMException;

import static com.yuge.yimcommon.enums.StatusEnum.ARGUMENT_CHECK_FAIL;

public class RouteInfoParseUtil {

    public static RouteInfo parse(String serverInfo){
        // 127.0.0.1:11211:8081
        String[] info = serverInfo.split(":");
        try{
            RouteInfo routeInfo = new RouteInfo()
                    .setIp(info[0])
                    .setCimServerPort(Integer.valueOf(info[1]))
                    .setHttpPort(Integer.valueOf(info[2]));
            return routeInfo;
        }catch (Exception e){
            throw new YIMException(ARGUMENT_CHECK_FAIL);
        }

    }
}
