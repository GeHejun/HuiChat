package com.ghj.chat;

import com.ghj.protocol.Message;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import static com.ghj.chat.MessageQueue.dealMsg;

/**
 * @author GeHejun
 * @date 2019-06-24
 */
@ChannelHandler.Sharable
public class ConnectHandler extends SimpleChannelInboundHandler<Message.Data> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message.Data data) throws Exception {
        dealMsg(channelHandlerContext, data);
    }
}
