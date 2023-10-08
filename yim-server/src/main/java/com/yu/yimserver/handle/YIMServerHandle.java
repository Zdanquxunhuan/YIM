package com.yu.yimserver.handle;

import com.yuge.yimcommon.constant.MessageType;
import com.yuge.yimcommon.message.YIMMessage;
import com.yuge.yimcommon.protocol.CIMRequestProto;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * Indicates that the same instance of the annotated ChannelHandler
 * can be added to one or more ChannelPipelines multiple times without a race condition.
 * If this annotation is not specified, you have to create a new handler instance every time
 * you add it to a pipeline because it has unshared state such as member variables.
 */
@ChannelHandler.Sharable
@Slf4j
public class YIMServerHandle extends SimpleChannelInboundHandler<YIMMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, YIMMessage msg) throws Exception {
        log.info("received msg=[{}]",msg.toString());

        if(MessageType.LOGIN == msg.getType()){
            //Saves the relationship between the client and Channel

        }
    }
}
