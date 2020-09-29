package com.ghj.access.keep;

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
        switch (data.getDataType()) {
            case ACK:

        }
    }

    public synchronized void sendMsg(Msg.Data data) {
        synchronized (lock) {
            if (Objects.isNull(ctx)) {
                log.error("keepClientChannel为空，请检查是否连接到Router");
            }
            switch (data.getDataType()) {
                case REGISTER:
                    ctx.writeAndFlush(data.getRegister());
                    break;
                case ROUTE_ALL:
                    ctx.writeAndFlush(data.getRouteAll());
                    break;
            }

        }
    }

}
