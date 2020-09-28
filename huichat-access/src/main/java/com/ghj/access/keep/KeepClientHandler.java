package com.ghj.access.keep;

import com.ghj.protocol.Msg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class KeepClientHandler extends SimpleChannelInboundHandler<Msg.Data> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Msg.Data data) throws Exception {

    }

}
