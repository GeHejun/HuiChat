package com.ghj.registry.client;

import com.ghj.protocol.Msg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class RegisterHandler extends SimpleChannelInboundHandler<Msg.Data> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Msg.Data msg) throws Exception {

    }
}
