package com.ghj.common.netty;

import com.ghj.protocol.MessageProto;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/8/27 13:40
 */
public class NettyChannelInitializer extends ChannelInitializer {

    SimpleChannelInboundHandler handler;

    public NettyChannelInitializer(SimpleChannelInboundHandler handler) {
        this.handler = handler;
    }

    @Override
    protected void initChannel(Channel channel) {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new ProtobufEncoder());
        pipeline.addLast(new ProtobufDecoder(MessageProto.Message.getDefaultInstance()));
        pipeline.addLast(handler);
    }
}
