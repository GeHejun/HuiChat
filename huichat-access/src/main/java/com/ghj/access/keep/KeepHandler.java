package com.ghj.access.keep;

import com.ghj.protocol.Msg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.ghj.common.constant.Constant.CHANNEL_ID;
//TODO 所有的消息发送必须加回调
@Component
@Slf4j
public class KeepHandler extends SimpleChannelInboundHandler<Msg.Data> {


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
                dealChatMsg(data.getChat());
                break;
            default:
        }
    }

    private void dealChatMsg(Msg.Chat chat) {

    }




    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    }
}
