package com.ghj.access.keep;

import com.alibaba.fastjson.JSON;
import com.ghj.protocol.Msg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class KeepClientHandler extends SimpleChannelInboundHandler<Msg.Data> {

    private final static Object lock = new Object();

    ChannelHandlerContext ctx;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Msg.Data data) throws Exception {
        dealMsg(data);

    }

    private void dealMsg(Msg.Data data) {
        switch (data.getDataType()) {
            case SYS_MSG:
                dealSysMsg(data);
                break;
            case CHAT:
                dealChatMsg(data);
                break;


        }
    }

    private void dealSysMsg(Msg.Data data) {
        Msg.SysMsg sysMsg = data.getSysMsg();
        switch (sysMsg.getMsgType()) {
            case ROUTING:
                Msg.SysMsg.Routing routing = sysMsg.getRouting();
            case ACK:

        }

    }

    private void dealChatMsg(Msg.Data data) {
        Msg.Chat chat = data.getChat();

    }

    public synchronized void sendMsg(Msg.Data data) {
        synchronized (lock) {
            if (Objects.isNull(ctx)) {
                log.error("keepClientChannel为空，请检查是否连接到Router");
            }

        }
    }

}
