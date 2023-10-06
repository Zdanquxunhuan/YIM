package com.yuge.yimcommon.route.algorithm.random;

import com.yuge.yimcommon.exception.YIMException;
import com.yuge.yimcommon.route.algorithm.RouteHandle;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.yuge.yimcommon.enums.StatusEnum.SERVER_NOT_AVAILABLE;

public class RandomHandle implements RouteHandle {

    @Override
    public String routeServer(List<String> serversAddr, String userId) {

        int size = serversAddr.size();
        if(size==0)
            throw new YIMException(SERVER_NOT_AVAILABLE);

        //Returns a pseudorandom int value between zero (inclusive) and the specified bound (exclusive).
        int offset = ThreadLocalRandom.current().nextInt(size);

        return serversAddr.get(offset);
    }
}
