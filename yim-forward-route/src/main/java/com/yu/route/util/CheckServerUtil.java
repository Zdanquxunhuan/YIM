package com.yu.route.util;

import com.yu.route.cache.ServerCache;
import com.yu.route.dto.RouteInfo;
import com.yuge.yimcommon.enums.StatusEnum;
import com.yuge.yimcommon.exception.YIMException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.yuge.yimcommon.enums.StatusEnum.SERVER_NOT_AVAILABLE;

@Slf4j
@Component
public class CheckServerUtil {

    @Autowired
    private ServerCache serverCache;

    public boolean isAvailable(RouteInfo routeInfo) {

        boolean reachable = NetAddressIsReachable.checkAddressReachable(routeInfo.getIp(), routeInfo.getCimServerPort(), 1000);
        if (!reachable) {
            log.error("ip={}, port={} are not available", routeInfo.getIp(), routeInfo.getCimServerPort());

            serverCache.rebuildServersCache();

            throw new YIMException(SERVER_NOT_AVAILABLE);
        }

        return true;
    }
}
