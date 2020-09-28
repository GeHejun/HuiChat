package com.ghj.access.keep;

import com.ghj.access.umps.UserService;
import com.ghj.protocol.Msg;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

import static com.ghj.common.constant.Constant.CHANNEL_ID;
//TODO 所有的消息发送必须加回调
@Component
@Slf4j
public class KeepHandler extends SimpleChannelInboundHandler<Msg.Data> {

    /**
     * 心跳丢失次数
     */
    private int counter = 0;


    @Resource
    UserService userService;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        String channelId = ctx.channel().id().asLongText();
        ctx.channel().attr(AttributeKey.valueOf(CHANNEL_ID)).set(channelId);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext chc, Msg.Data data) {
        switch (data.getDataType()) {
            case Login:
                dealLoginMsg(chc.channel(), data.getLogin());
                break;
            case Logout:
                dealLogoutMsg(data.getLogout());
                break;
            case Chat:
                dealChatMsg(data.getChat());
                break;
            default:
        }
    }

    private void dealChatMsg(Msg.Chat chat) {

    }

    private void dealLogoutMsg(Msg.Logout logout) {
        userService.logout(logout);
    }


    private void dealLoginMsg(Channel channel, Msg.Login login) {
        String channelId = (String)channel.attr(AttributeKey.valueOf(CHANNEL_ID)).get();
        String host = ((InetSocketAddress) channel.remoteAddress()).getHostString();
        Boolean isLogin = userService.login(channelId, host, login);
        Msg.Ack.Status status;
        if (isLogin) {
            status = Msg.Ack.Status.LoginSuc;
        } else {
            status = Msg.Ack.Status.LoginFail;
        }
        Msg.Ack ack = Msg.Ack.newBuilder()
                .setStatus(status)
                .setMsgId(login.getId())
                .build();
        channel.eventLoop().execute(() -> channel.writeAndFlush(ack));
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    }
}
