package com.yu.yimserver;

import com.yu.yimserver.config.AppConfiguration;
import com.yu.yimserver.kit.RegisterZK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;

@SpringBootApplication(scanBasePackages = {"com.yu.yimserver","com.yuge.yimcommon.yulog"})
public class YimServerApplication implements CommandLineRunner {

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private AppConfiguration appConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(YimServerApplication.class, args);
    }

    //The server is registered with zookeeper upon startup
    @Override
    public void run(String... args) throws Exception {
        //Get Local IP
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        Thread thread = new Thread(new RegisterZK(hostAddress,port,appConfiguration.getYimServerPort()));
        thread.setName("register server to Zookeeper");
        thread.start();
    }
}
