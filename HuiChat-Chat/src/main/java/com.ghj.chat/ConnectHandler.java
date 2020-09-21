package com.ghj.chat;

import com.ghj.common.base.Constant;
import com.ghj.common.exception.UserException;
import com.ghj.common.util.*;
import com.ghj.protocol.Message;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.RetryNTimes;

import java.net.InetSocketAddress;
import java.util.Date;

import static com.ghj.chat.MessageQueue.dealMsg;

/**
 * @author GeHejun
 * @date 2019-06-24
 */
@ChannelHandler.Sharable
public class ConnectHandler extends SimpleChannelInboundHandler<Message.Data> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message.Data data) throws Exception {
        dealMsg(channelHandlerContext, data);
    }
}
