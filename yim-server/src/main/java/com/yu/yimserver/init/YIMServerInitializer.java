package com.yu.yimserver.init;

import com.yu.yimserver.handle.YIMServerHandle;
import com.yuge.yimcommon.protocol.CIMRequestProto;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * Function: Netty的服务器初始化器，作用是在Channel创建后对其进行初始化
 */
public class YIMServerInitializer extends ChannelInitializer<Channel> {

    //创建一个YIMServerHandle实例，用于处理接收到的请求
    private final YIMServerHandle yimServerHandle = new YIMServerHandle();

    @Override
    protected void initChannel(Channel ch) throws Exception {

        ch.pipeline()
                .addLast(yimServerHandle); //将YIMServerHandle实例添加到Channel的pipeline中，用于处理解码后的CIMRequestProto.CIMReqProtocol对象
    }
}
