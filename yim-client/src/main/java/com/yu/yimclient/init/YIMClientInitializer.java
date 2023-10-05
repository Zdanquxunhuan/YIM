package com.yu.yimclient.init;

import com.yu.yimclient.handle.YIMClientHandle;
import com.yuge.yimcommon.protocol.CIMResponseProto;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

//which offers an easy way to initialize a Channel
public class YIMClientInitializer extends ChannelInitializer<Channel> {

    private final YIMClientHandle yimClientHandle = new YIMClientHandle();

    @Override
    protected void initChannel(Channel ch) throws Exception {

        ch.pipeline()
                .addLast(new IdleStateHandler(0, 10, 0))
                .addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new ProtobufDecoder(CIMResponseProto.CIMResProtocol.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder())
                .addLast(yimClientHandle);

    }
}
