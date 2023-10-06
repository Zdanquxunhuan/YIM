package com.yu.yimclient.client;

import com.yu.yimclient.config.AppConfiguration;
import com.yu.yimclient.dto.LoginReqDTO;
import com.yu.yimclient.dto.YIMServerResDTO;
import com.yu.yimclient.handle.YIMClientHandle;
import com.yu.yimclient.init.YIMClientInitializer;
import com.yu.yimclient.service.RouteRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class YIMClient {

    @Value("${yim.client.userId}")
    private Long userId;

    @Value("${yim.client.userName}")
    private String userName;

    private EventLoopGroup group = new NioEventLoopGroup(0, new DefaultThreadFactory("yim-client-work"));

    @Autowired
    private RouteRequest routeRequest;

    @Autowired
    private AppConfiguration appConfiguration;

    //Retry times
    private int errorCount;

    @PostConstruct
    public void start() {

        YIMServerResDTO.ServerInfo serverInfo = loginAndGetServer();

        startClient();
    }


    private YIMServerResDTO.ServerInfo loginAndGetServer() {
        LoginReqDTO loginReqDTO = new LoginReqDTO(userId, userName);
        YIMServerResDTO.ServerInfo serverInfo = null;
        try{
            serverInfo = routeRequest.loginAndGetYIMServer(loginReqDTO);

            //Save client information
            log.info("login and get YIMServer=[{}] successfully ",serverInfo.toString());

        }catch (Exception e){
            //TODO 失败重试
            errorCount++;

            if(errorCount>appConfiguration.getReconnectCount()){
                //TODO 关闭系统
            }
        }
        return serverInfo;
    }



    /**
     * 创建并启动一个 Netty 客户端
     */
    private void startClient() {
        new Bootstrap()
                //The EventLoopGroup which is used to handle all the events for the to-be-created Channel
                .group(group)
                //The Class which is used to create Channel instances from
                .channel(NioSocketChannel.class)
                //为客户端的 Channel pipeline 添加处理器,主要用于处理网络数据的编解码、业务逻辑等
                .handler(new YIMClientInitializer());
    }
}
