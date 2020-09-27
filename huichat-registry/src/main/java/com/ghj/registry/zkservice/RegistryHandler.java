package com.ghj.registry.zkservice;


import com.ghj.protocol.Msg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;

//TODO 所有的消息发送必须加回调
@Component
public class RegistryHandler extends SimpleChannelInboundHandler<Msg.Data> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext chc, Msg.Data data) {

    }

}
