package com.yu.yimclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.yu.yimclient","com.yuge.yimcommon.yulog"})
public class YimClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(YimClientApplication.class, args);
    }

}
