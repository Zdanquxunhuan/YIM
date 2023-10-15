package com.yu.route;

import com.yu.route.kit.ServerListListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = {"com.yu.route","com.yuge.yimcommon.yulog"})
public class YimForwardRouteApplication implements CommandLineRunner {

    @Autowired
    private ServerListListener serverListListener;

    public static void main(String[] args) {
        SpringApplication.run(YimForwardRouteApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        //Listening service: The application listens to routing nodes in Zookeeper after startup,
        // and updates the corresponding internal cache once changes occur
        Thread thread = new Thread(serverListListener);
        thread.setName("zk-listener");
        thread.start();

    }
}
