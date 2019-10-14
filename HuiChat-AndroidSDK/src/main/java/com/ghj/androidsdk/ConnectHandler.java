package com.ghj.androidsdk;

import com.ghj.protocol.Message;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author GeHejun
 * @date 2019-06-24
 */
@ChannelHandler.Sharable
public class ConnectHandler extends SimpleChannelInboundHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) {
        System.out.println(o);
        Message.Data data = (Message.Data) o;
        Channel channel = channelHandlerContext.channel();
        switch (data.getDataType()) {
            case Chat:
                Message.Chat chat = data.getChat();
                Message.Data ack = Message.Data.newBuilder().setDataType(Message.Data.DataType.Ack).setAck(Message.Ack.newBuilder().setMsgId(chat.getId()).setTo(chat.getForm()).setAckStatus(Message.Ack.AckStatus.Receive).build()).build();
                channel.writeAndFlush(ack);
                break;
            case Ack:

            case UNRECOGNIZED:
            default:
        }
    }

}
