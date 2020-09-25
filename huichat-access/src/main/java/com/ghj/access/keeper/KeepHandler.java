package com.ghj.access.keeper;

import com.alibaba.fastjson.JSON;
import com.ghj.common.HSStatusEnum;
import com.ghj.common.HSession;
import com.ghj.protocol.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Instant;

import static com.ghj.common.constant.Constant.CHANNEL_ID;

@Component
public class KeepHandler extends SimpleChannelInboundHandler<Message.Data> {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        String channelId = ctx.channel().id().asLongText();
        ctx.channel().attr(AttributeKey.valueOf(CHANNEL_ID)).set(channelId);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message.Data data) {
        String channelId = (String)channelHandlerContext.channel().attr(AttributeKey.valueOf(CHANNEL_ID)).get();
        switch (data.getDataType()) {
            case Login:
                dealLoginMsg(channelId, data);
                break;
            case Logout:
                dealLoutMsg(data);
                break;
        }
    }

    private void dealLoutMsg(Message.Data data) {
        stringRedisTemplate.delete(data.getLogout().getForm());
    }

    /**
     * 登陆人加入session
     * @param channelId
     * @param data
     */
    private void dealLoginMsg(String channelId, Message.Data data) {
        HSession hSession = new HSession()
                .setChannelId(channelId)
                .setCreateTime(Instant.now().toEpochMilli())
                .setStatus(HSStatusEnum.ON_LINE.getCode())
                .setUId(data.getLogin().getForm());
        stringRedisTemplate.opsForValue().set(data.getLogin().getForm(), JSON.toJSONString(hSession));
    }
}
