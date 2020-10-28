package com.ghj.registry.client;

import com.ghj.protocol.Msg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import static com.ghj.protocol.Msg.Data.DataType.SYS_MSG;
import static com.ghj.protocol.Msg.SysMsg.MsgType.ROUTING;

public class RegisterHandler extends SimpleChannelInboundHandler<Msg.Data> {

    private RoutingMsgHandler routingMsgHandler;

    public RegisterHandler(RoutingMsgHandler routingMsgHandler) {
        this.routingMsgHandler = routingMsgHandler;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Msg.Data msg) throws Exception {
        dealMsg(msg);
    }

    private void dealMsg(Msg.Data data) {
        if (SYS_MSG == data.getDataType()) {
            Msg.SysMsg sysMsg = data.getSysMsg();
            if (ROUTING == sysMsg.getMsgType()) {
                Msg.SysMsg.Routing routing = sysMsg.getRouting();
                routingMsgHandler.dealRoutingMsg(routing);
            }
        } else {

        }

    }


}
