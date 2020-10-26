package com.ghj.access.keep;

import com.ghj.protocol.Msg;
import com.google.common.collect.Lists;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

import static com.ghj.protocol.Msg.SysMsg.MsgType.ROUTING;

@Slf4j
public class KeepClientHandler extends SimpleChannelInboundHandler<Msg.Data> {


    private ChannelHandlerContext ctx;

    private List<MsgCallBack> msgCallBacks;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Msg.Data data) throws Exception {
        dealMsg(data);
    }

    private void dealMsg(Msg.Data data) {
        msgCallBacks.forEach(msgCallBack -> msgCallBack.back(data));
        switch (data.getDataType()) {
            case CHAT:
                dealChatMsg(data);
                break;
            case SYS_MSG:
                dealSysMsg(data);
                break;
            default:
        }
    }

    private void dealSysMsg(Msg.Data data) {
        Msg.SysMsg sysMsg = data.getSysMsg();
        if (ROUTING == sysMsg.getMsgType()) {
            Msg.SysMsg.Routing routing = sysMsg.getRouting();
            routing.getAddressList().forEach(this::connectRouter);
        }
    }

    public void connectRouter(String address) {
        KeepClientContext.adjustKeepClient(address);
    }

    private void dealChatMsg(Msg.Data data) {
        if (KeepContext.checkServer()) {
            Msg.Chat chat = data.getChat();
        } else {

        }

    }

    public void sendMsg(Msg.Data data, List<MsgCallBack> msgCallBacks) {
        if (Objects.isNull(ctx)) {
            log.error("keepClientChannel为空，请检查是否连接到Router");
            throw new RuntimeException("XXX");
        }
        this.msgCallBacks = msgCallBacks;
        ctx.writeAndFlush(data);
    }

}
