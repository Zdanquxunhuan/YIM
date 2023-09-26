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
                //11 秒没有向客户端发送消息就发生心跳
                //在Channel的pipeline中添加了一个IdleStateHandler实例，用于检测长时间没有发送或接收数据的连接，并触发相应的事件
                .addLast(new IdleStateHandler(11, 0, 0))
                //在Channel的pipeline中添加了以下四个处理器：
                // google Protobuf 编解码
                .addLast(new ProtobufVarint32FrameDecoder()) //用于解决protobuf中的“拆包”问题
                .addLast(new ProtobufDecoder(CIMRequestProto.CIMReqProtocol.getDefaultInstance())) //将protobuf二进制数据解码成CIMRequestProto.CIMReqProtocol对象。
                .addLast(new ProtobufVarint32LengthFieldPrepender()) //用于解决protobuf中的“粘包”问题
                .addLast(new ProtobufEncoder()) //将CIMRequestProto.CIMReqProtocol对象编码成protobuf二进制数据
                .addLast(yimServerHandle); //将YIMServerHandle实例添加到Channel的pipeline中，用于处理解码后的CIMRequestProto.CIMReqProtocol对象
    }
}
