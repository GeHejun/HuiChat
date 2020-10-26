package com.ghj.access.keep;

import com.ghj.protocol.Msg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Random;

import static com.ghj.protocol.Msg.Data.DataType.SYS_MSG;
import static com.ghj.protocol.Msg.SysMsg.MsgType.GREET;

//TODO 所有的消息发送必须加回调
@Component
@Slf4j
public class KeepHandler extends SimpleChannelInboundHandler<Msg.Data> {

    @Resource
    StringRedisTemplate stringRedisTemplate;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Msg.Data data) {
        dealMsg(ctx, data);
    }


    private void dealMsg(ChannelHandlerContext ctx, Msg.Data data) {
        KeepContext.checkServer();
        if (SYS_MSG == data.getDataType()) {
            dealSysMsg(ctx, data.getSysMsg());
        } else {
            sendChatMsgToRouter(data);
        }
    }

    private void sendChatMsgToRouter(Msg.Data data) {
        KeepClient keepClient = KeepClientContext.getBestKeepClint();
        keepClient.sendMsg(data);
    }

    private void dealSysMsg(ChannelHandlerContext ctx, Msg.SysMsg sysMsg) {
        if (KeepContext.checkServer()) {
            if (GREET == sysMsg.getMsgType()) {
                buildContext(ctx, sysMsg);
            }
        } else {
            log.error("连接失败，服务器不可用");
        }
    }

    private void buildContext(ChannelHandlerContext ctx, Msg.SysMsg sysMsg) {
        Msg.SysMsg.Greet greet = sysMsg.getGreet();
        HSessionContext.addHSession(new HSession()
                .setCreateTime(greet.getTimestamp())
                .setCtx(ctx).setClientAddress(ctx.channel().remoteAddress().toString())
                .setLocation(greet.getLocation()).setUId(greet.getUId()));
        try {
            stringRedisTemplate.opsForValue().setIfPresent(String.valueOf(greet.getUId()), InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            log.error("连接失败，服务器内部问题，无法获取本地服务地址:{}", e.toString());
        }
    }


}
