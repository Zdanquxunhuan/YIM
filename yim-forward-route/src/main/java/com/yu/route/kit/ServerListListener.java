package com.yu.route.kit;

import com.yu.route.config.AppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerListListener implements Runnable{

    @Autowired
    private ZKKit zkKit;

    @Autowired
    private AppConfiguration appConfiguration;

    public ServerListListener(ZKKit zkKit, AppConfiguration appConfiguration) {
        this.zkKit = zkKit;
        this.appConfiguration = appConfiguration;
    }

    @Override
    public void run() {
        zkKit.subscribeEvent(appConfiguration.getZkRoot());
    }

}
