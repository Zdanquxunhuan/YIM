package com.yu.yimclient.client;

import com.yu.yimclient.config.AppConfiguration;
import com.yu.yimclient.dto.GoogleProtocolDTO;
import com.yu.yimclient.dto.LoginReqDTO;
import com.yu.yimclient.dto.YIMServerResDTO;
import com.yu.yimclient.handle.YIMClientHandle;
import com.yu.yimclient.init.YIMClientInitializer;
import com.yu.yimclient.service.RouteRequest;
import com.yuge.yimcommon.constant.MessageType;
import com.yuge.yimcommon.protocol.CIMRequestProto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
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

    private SocketChannel channel;

    @PostConstruct
    public void start() {

        YIMServerResDTO.ServerInfo serverInfo = loginAndGetServer();

        startClient(serverInfo);

        loginYIMClient();
    }

    private void loginYIMClient() {

        CIMRequestProto.CIMReqProtocol login = CIMRequestProto.CIMReqProtocol.newBuilder()
                .setRequestId(userId)
                .setReqMsg(userName)
                .setType(MessageType.LOGIN)
                .build();

        ChannelFuture future = channel.writeAndFlush(login);
        //Adds the specified listener to this future. The specified listener is notified when this future is done.
        future.addListener(channelFuture -> {
            if (channelFuture.isSuccess()) {
                // 发送成功的处理逻辑
                System.out.println("消息发送成功！");
            } else {
                // 发送失败的处理逻辑
                System.err.println("消息发送失败：" + channelFuture.cause());
            }
            log.info("client login success");
        });
    }


    private YIMServerResDTO.ServerInfo loginAndGetServer() {
        LoginReqDTO loginReqDTO = new LoginReqDTO(userId, userName);
        YIMServerResDTO.ServerInfo serverInfo = null;
        try {
            serverInfo = routeRequest.loginAndGetYIMServer(loginReqDTO);

            //Save client information
            log.info("login and get YIMServer=[{}] successfully ", serverInfo.toString());

        } catch (Exception e) {
            //TODO 失败重试
            errorCount++;

            if (errorCount > appConfiguration.getReconnectCount()) {
                //TODO 关闭系统
            }
        }
        return serverInfo;
    }


    /**
     * 创建并启动一个 Netty 客户端
     */
    private void startClient(YIMServerResDTO.ServerInfo serverInfo) {
        Bootstrap bootstrap = new Bootstrap()
                //The EventLoopGroup which is used to handle all the events for the to-be-created Channel
                .group(group)
                //The Class which is used to create Channel instances from
                .channel(NioSocketChannel.class)
                //为客户端的 Channel pipeline 添加处理器,主要用于处理网络数据的编解码、业务逻辑等
                .handler(new YIMClientInitializer());

        //Returns a ChannelFuture object representing the asynchronous result of the connection operation
        ChannelFuture connect = null;
        try {
            connect = bootstrap.connect(serverInfo.getIp(), serverInfo.getYimServerPort()).sync();
        } catch (Exception e) {
            //TODO Reconnect after a failure

            //TODO If the number of retries exceeds, shut down the system

        }

        if (connect.isSuccess())
            log.info("启动yim client 成功");

        channel = (SocketChannel) connect.channel();
    }

    /**
     * 发送 Google Protocol 编解码字符串
     *
     * @param googleProtocolVO
     */
    public void sendGoogleProtocolMsg(GoogleProtocolDTO googleProtocolVO) {

        CIMRequestProto.CIMReqProtocol protocol = CIMRequestProto.CIMReqProtocol.newBuilder()
                .setRequestId(googleProtocolVO.getRequestId())
                .setReqMsg(googleProtocolVO.getMsg())
                .setType(MessageType.CHAT)
                .build();


        ChannelFuture future = channel.writeAndFlush(protocol);
        future.addListener((ChannelFutureListener) channelFuture ->{
                    log.info("客户端手动发送 Google Protocol ={}", googleProtocolVO);
                });

    }
}
