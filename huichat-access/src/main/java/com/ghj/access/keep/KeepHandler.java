package com.ghj.access.keep;

import com.ghj.protocol.Msg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Random;

import static com.ghj.protocol.Msg.Data.DataType.SYS_MSG;
import static com.ghj.protocol.Msg.SysMsg.MsgType.GREET;

//TODO 所有的消息发送必须加回调
@Component
@Data
@Slf4j
public class KeepHandler extends SimpleChannelInboundHandler<Msg.Data> {

    private Random random = new Random();

    List<KeepClient> keepClients;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Msg.Data data) {
        dealMsg(ctx, data);
    }


    private void dealMsg(ChannelHandlerContext ctx, Msg.Data data) {
        if (SYS_MSG == data.getDataType()) {
            buildContext(ctx, data.getSysMsg());
        } else {
            sendToRouter(data);
        }

    }

    private void sendToRouter(Msg.Data data) {
        int index = random.nextInt(keepClients.size());
        KeepClient keepClient = keepClients.get(index);
        keepClient.sendMsg(data);
    }

    private void buildContext(ChannelHandlerContext ctx, Msg.SysMsg sysMsg) {
        if (GREET == sysMsg.getMsgType()) {
            Msg.SysMsg.Greet greet = sysMsg.getGreet();
            HSession hSession = new HSession().setCreateTime(greet.getTimestamp())
                    .setCtx(ctx).setClientAddress(ctx.channel().remoteAddress().toString())
                    .setLocation(greet.getLocation()).setUId(greet.getUId());
            KeepContext.addHSession(hSession);
            try {
                stringRedisTemplate.opsForValue().setIfPresent(String.valueOf(greet.getUId()), Inet4Address.getLocalHost().getHostAddress());
            } catch (UnknownHostException e) {
                log.error("连接失败，服务器内部问题，无法获取本地服务地址:{}", e.toString());
            }
        }
    }
}
