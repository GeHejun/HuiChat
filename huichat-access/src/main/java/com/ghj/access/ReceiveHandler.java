package com.ghj.access;

import com.ghj.protocol.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ReceiveHandler extends SimpleChannelInboundHandler<Message.Data> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message.Data data) throws Exception {
        switch (data.getDataType()) {
            case Login:
                dealLoginMsg(data);
                break;
            case Logout:
                dealLoutMsg(data);
                break;
        }
    }

    private void dealLoutMsg(Message.Data data) {
    }

    private void dealLoginMsg(Message.Data data) {
    }
}
