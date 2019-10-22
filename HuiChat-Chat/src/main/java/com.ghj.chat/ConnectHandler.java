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

/**
 * @author GeHejun
 * @date 2019-06-24
 */
@ChannelHandler.Sharable
public class ConnectHandler extends SimpleChannelInboundHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) {
        System.out.println(o);
        Message.Data data = (Message.Data) o;
        Channel channel = channelHandlerContext.channel();
        switch (data.getDataType()) {
            case Login:
                Message.Login login = data.getLogin();
                validateUser(login);
                NettyAttrUtil.updateReaderTime(channel, System.currentTimeMillis() + Constant.PING_ADD_TIME);
                Session session = Session.builder().channel(channel)
                        .userId(login.getForm())
                        .build();
                SessionManager.putSession(login.getForm(), session);
                incrementOnLineUser(channel);
                MessageSender.sendMsg(data);
                channel.writeAndFlush(Message.Data.newBuilder().setDataType(Message.Data.DataType.Ack).setAck(Message.Ack.newBuilder().setTo(login.getForm()).setAckStatus(Message.Ack.AckStatus.Receive).build()).build());
                break;
            case Ping:
                NettyAttrUtil.updateReaderTime(channel, System.currentTimeMillis() + Constant.PING_ADD_TIME);
                break;
            case Chat:
                Message.Chat chat = data.getChat();
                validateToken(chat);
                MessageSender.sendMsg(data);
                channel.writeAndFlush(Message.Data.newBuilder().setDataType(Message.Data.DataType.Ack).setAck(Message.Ack.newBuilder().setTo(chat.getForm()).setMsgId(chat.getId()).setAckStatus(Message.Ack.AckStatus.Receive).build()).build());
                break;
            case Logout:
                Message.Logout logout = data.getLogout();
                SessionManager.removeSession(logout.getForm());
                channel.writeAndFlush(Message.Data.newBuilder().setDataType(Message.Data.DataType.Ack).setAck(Message.Ack.newBuilder().setTo(logout.getForm()).setAckStatus(Message.Ack.AckStatus.Receive).build()).build());
                break;
            case Ack:
                Message.Ack ack = data.getAck();
                MessageSender.sendMsg(data);
                channel.writeAndFlush(Message.Data.newBuilder().setDataType(Message.Data.DataType.Ack).setAck(Message.Ack.newBuilder().setMsgId(ack.getMsgId()).setAckStatus(Message.Ack.AckStatus.Receive).build()).build());
                break;
            case UNRECOGNIZED:
            default:
        }
    }

    private void validateUser(Message.Login login) {
        String token = RedisPoolUtil.get(Constant.SYSTEM_PREFIX + Constant.USER_TOKEN_KEY + login.getForm());
        if (StringUtils.isEmpty(token)) {
            throw new UserException();
        }
    }

    private void validateToken(Message.Chat chat) {
        Long time = RedisPoolUtil.ttl(Constant.SYSTEM_PREFIX + Constant.USER_TOKEN_KEY + chat.getForm());
        if (time <= 0) {
            throw new UserException();
        }
    }

    private synchronized void incrementOnLineUser(Channel channel) {
        try {
            String connect = PropertiesUtil.getInstance().getValue(Constant.ZOOKEEPER_CONNECT);
            CuratorFramework client = ZookeeperUtil.getInstance(connect);
            InetSocketAddress address = (InetSocketAddress) channel.localAddress();
            String path = Constant.SERVER_NODE + address.getAddress() + ":" + address.getPort();
            DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(client, path, new RetryNTimes(3, 1000));
            atomicInteger.increment();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
