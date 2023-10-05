package com.yu.yimclient.handle;

import com.yuge.yimcommon.protocol.CIMResponseProto;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@ChannelHandler.Sharable
@Slf4j
//ChannelInboundHandlerAdapter which allows to explicit only handle a specific type of messages
public class YIMClientHandle extends SimpleChannelInboundHandler {

    //messageReceived
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

    }




}
