package com.ghj.access.keep;

import com.ghj.protocol.Msg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

//TODO 所有的消息发送必须加回调
@Component
@Data
@Slf4j
public class KeepHandler extends SimpleChannelInboundHandler<Msg.Data> {

    private static Random random = new Random();

    List<KeepClient> keepClients;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Msg.Data data) {
        dealMsg(ctx, data);
    }


    private void dealMsg(ChannelHandlerContext ctx, Msg.Data chat) {
        buildContext(ctx, chat);
        sendToRouter(chat);
    }

    private void sendToRouter(Msg.Data data) {
        int index = random.nextInt(keepClients.size());
        KeepClient keepClient = keepClients.get(index);
        keepClient.sendMsg(data);
    }

    private void buildContext(ChannelHandlerContext ctx, Msg.Data data) {
    }
}
