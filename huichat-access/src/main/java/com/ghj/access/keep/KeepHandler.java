package com.ghj.access.keep;

import com.ghj.protocol.Msg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

import static com.ghj.common.constant.Constant.CHANNEL_ID;
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
        String channelId = ctx.channel().id().asLongText();
        ctx.channel().attr(AttributeKey.valueOf(CHANNEL_ID)).set(channelId);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext chc, Msg.Data data) {
        switch (data.getDataType()) {
            case CHAT:
                dealChatMsg(data);
                break;
            case ACK:
                dealAckMsg(data);
                break;
            default:
        }
    }

    private void dealAckMsg(Msg.Data data) {

    }

    private void dealChatMsg(Msg.Data chat) {
        int index = random.nextInt(keepClients.size());
        KeepClient keepClient = keepClients.get(index);
        keepClient.sendMsg(chat);
    }




    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    }
}
