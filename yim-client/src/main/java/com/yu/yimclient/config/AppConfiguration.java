package com.yu.yimclient.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class AppConfiguration {

    @Value("${yim.client.userId}")
    private Long userId;

    @Value("${yim.client.userName}")
    private String userName;

    @Value("${yim.reconnect.count}")
    private int reconnectCount;

}
