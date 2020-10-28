package com.ghj.registry.client;

import com.ghj.protocol.Msg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

import static com.ghj.protocol.Msg.Data.DataType.SYS_MSG;
import static com.ghj.protocol.Msg.SysMsg.MsgType.ROUTING;

@Slf4j
public class RegisterHandler extends SimpleChannelInboundHandler<Msg.Data> {

    private ChannelHandlerContext ctx;

    private RoutingMsgHandler routingMsgHandler;

    private List<RegisterMsgCallBack> msgCallBacks;

    public RegisterHandler(RoutingMsgHandler routingMsgHandler) {
        this.routingMsgHandler = routingMsgHandler;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Msg.Data msg) throws Exception {
        dealMsg(msg);
    }

    private void dealMsg(Msg.Data data) {
        msgCallBacks.forEach(msgCallBack -> msgCallBack.back(data));
        if (SYS_MSG == data.getDataType()) {
            Msg.SysMsg sysMsg = data.getSysMsg();
            if (ROUTING == sysMsg.getMsgType()) {
                Msg.SysMsg.Routing routing = sysMsg.getRouting();
                routingMsgHandler.dealRoutingMsg(routing);
            }
        } else {

        }

    }


    public void sendMsg(Msg.Data data, List<RegisterMsgCallBack> msgCallBacks) {
        if (Objects.isNull(ctx)) {
            log.error("keepClientChannel为空，请检查是否连接到Router");
            throw new RuntimeException("XXX");
        }
        this.msgCallBacks = msgCallBacks;
        ctx.writeAndFlush(data);
    }

}
