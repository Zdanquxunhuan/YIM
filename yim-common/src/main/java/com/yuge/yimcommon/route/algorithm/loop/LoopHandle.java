package com.yuge.yimcommon.route.algorithm.loop;

import com.yuge.yimcommon.exception.YIMException;
import com.yuge.yimcommon.route.algorithm.RouteHandle;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.yuge.yimcommon.enums.StatusEnum.SERVER_NOT_AVAILABLE;

public class LoopHandle implements RouteHandle {

    AtomicInteger index = new AtomicInteger(0);

    @Override
    public String routeServer(List<String> serversAddr, String userId) {

        int size = serversAddr.size();
        if(size==0)
            throw new YIMException(SERVER_NOT_AVAILABLE);

        int position = index.getAndIncrement() % size;

        return serversAddr.get(position);
    }
}
