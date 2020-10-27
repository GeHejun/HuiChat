package com.ghj.router;

import com.ghj.protocol.Msg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;

@Component
public class RouteHandler extends SimpleChannelInboundHandler<Msg.Data> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Msg.Data msg) throws Exception {

    }
}
