package com.yu.yimserver.server;

import com.yu.yimserver.init.YIMServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

@Component
@Slf4j
public class CIMServer {

    //创建线程池处理客户端连接请求(boss)，和处理客户端请求（worker）
    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup worker = new NioEventLoopGroup();

    @Value("${cim.server.port}")
    private int nettyPort;

    /**
     * 启动 yim server
     *
     * 创建一个基于 Netty 框架的服务器，并启动监听指定的网络端口，用于接收客户端的请求并响应
     */
    @PostConstruct
    public void start() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap() //创建一个 Netty 服务器启动器
                .group(boss,worker) //设置服务器的两个线程组，boss 线程组用于接收客户端的连接请求，work 线程组用于处理客户端请求和服务端响应
                // uses NIO selector based implementation to accept new connections.
                .channel(NioServerSocketChannel.class)  //指定使用 NIO 的网络通道来接收客户端连接请求
                .localAddress(new InetSocketAddress(nettyPort)) //指定服务器要监听的网络端口号
                .childOption(ChannelOption.SO_KEEPALIVE,true) //设置客户端的连接为长连接，保持连接状态
                .childHandler(new YIMServerInitializer()); //为客户端连接的 Channel 设置处理器，用于处理客户端请求和服务端响应

        ChannelFuture future = bootstrap.bind().sync(); //绑定并启动服务器，该方法会一直阻塞直到服务器启动完成
        if(future.isSuccess())
            log.info("Start yim server success!!!");

    }

    @PreDestroy
    public void destroy(){
        boss.shutdownGracefully().syncUninterruptibly();
        worker.shutdownGracefully().syncUninterruptibly();
        log.info("Close yim server successfully");
    }
}
