package com.yu.yimserver.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
public class AppConfiguration {

    @Value("${app.zk.root}")
    private String zkRoot;

    @Value("${app.zk.addr}")
    private String zkAddr;

    @Value("${app.zk.switch}")
    private boolean zkSignUp;

    @Value("${app.zk.connect.timeout}")
    private int zkConnectTimeout;

    @Value("${yim.server.port}")
    private int yimServerPort;

}
