package com.yu.yimserver.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
public class AppConfiguration {

    @Value("${app.forbug.zk.root}")
    private String zkRoot;

    @Value("${app.forbug.zk.addr}")
    private String zkAddr;

    @Value("${app.forbug.zk.signup}")
    private boolean zkSignUp;

    @Value("${app.forbug.zk.connect.timeout}")
    private int zkConnectTimeout;

    @Value("${yim.server.port}")
    private int yimServerPort;

}
