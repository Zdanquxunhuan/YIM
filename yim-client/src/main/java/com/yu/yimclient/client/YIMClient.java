package com.yu.yimclient.client;

import com.yu.yimclient.dto.LoginReqDTO;
import com.yu.yimclient.dto.YIMServerResDTO;
import com.yu.yimclient.handle.YIMClientHandle;
import com.yu.yimclient.init.YIMClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class YIMClient {

    @Value("${yim.client.userId}")
    private Long userId;

    @Value("${yim.client.userName}")
    private String userName;

    private EventLoopGroup group = new NioEventLoopGroup(0,new DefaultThreadFactory("yim-client-work"));

    @PostConstruct
    public void start(){

        login();

        YIMServerResDTO.ServerInfo serverInfo = getAvailableServer();

        startClient();
    }

    private YIMServerResDTO.ServerInfo getAvailableServer() {
        return null;
    }

    //Write in the configuration file first,
    //and then develop the registration interface
    //TODO Login authentication
    private void login() {
        LoginReqDTO loginReqDTO = new LoginReqDTO(userId, userName);
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
