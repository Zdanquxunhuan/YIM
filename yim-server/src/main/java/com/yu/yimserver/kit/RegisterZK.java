package com.yu.yimserver.kit;

import com.yu.yimserver.config.AppConfiguration;
import com.yu.yimserver.util.SpringBeanFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
//@Component 构造器报错：Could not autowire. No beans of 'String' type found.
public class RegisterZK implements Runnable {

    private String ip;
    private int httpPort;
    private int yimServerPort;
    private ZKKit zkKit;
    private AppConfiguration appConfiguration;

    public RegisterZK(String ip, int httpPort, int yimServerPort) {
        this.ip = ip;
        this.httpPort = httpPort;
        this.yimServerPort = yimServerPort;
        this.zkKit = SpringBeanFactory.getBean(ZKKit.class);
        this.appConfiguration = SpringBeanFactory.getBean(AppConfiguration.class);
    }

    /**
     * On the client side (listening to the Zookeeper node),
     * you can see the current available service information in real time
     */
    @Override
    public void run() {
        zkKit.createRootNode();

        if (appConfiguration.isZkSignUp()) {
            String path = appConfiguration.getZkRoot() + "/ip-" + ip + ":" + yimServerPort + ":" + httpPort;
            zkKit.createNode(path);
            log.info("Registry zookeeper success, msg=[{}]", path);
        }
    }
}
