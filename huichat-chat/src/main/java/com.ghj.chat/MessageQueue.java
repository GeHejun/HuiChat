package com.ghj.chat;

import com.ghj.common.base.Constant;
import com.ghj.common.exception.UserException;
import com.ghj.protocol.Message;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.RetryNTimes;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue {
    private final static Queue<Message.Data> dataQueue = new LinkedBlockingQueue<>();

    public static void putMsg(Message.Data data) {
        ThreadPoolManager.getsInstance().execute(() -> dataQueue.add(data));
    }

    public static Message.Data getMsg() throws ExecutionException, InterruptedException {
        Future<Message.Data> submit = ThreadPoolManager.getsInstance().submit(dataQueue::poll);
        return submit.get();
    }

    public static void dealMsg(ChannelHandlerContext channelHandlerContext, Message.Data data) throws ExecutionException, InterruptedException {
        putMsg(data);
        //改多线程
        while (true) {
            data = getMsg();
            Channel channel = channelHandlerContext.channel();
            switch (data.getDataType()) {
                case Login:
                    dealLoginMsg(data, channel);
                    break;
                case Ping:
                    dealPingMsg(channel);
                    break;
                case Chat:
                    dealChatMsg(data, channel);
                    break;
                case Logout:
                    dealLogoutMsg(data, channel);
                    break;
                case Ack:
                    dealAckMsg(data, channel);
                    break;
                case UNRECOGNIZED:
                default:
            }
        }

    }


    private static void dealAckMsg(Message.Data data, Channel channel) {
        Message.Ack ack = data.getAck();
        MessageHandler.sendMsg(data);
        channel.writeAndFlush(Message.Data.newBuilder().setDataType(Message.Data.DataType.Ack).setAck(Message.Ack.newBuilder().setMsgId(ack.getMsgId()).setAckStatus(Message.Ack.AckStatus.Receive).build()).build());
    }

    private static void dealLogoutMsg(Message.Data data, Channel channel) {
        Message.Logout logout = data.getLogout();
        SessionManager.removeSession(logout.getForm());
        channel.writeAndFlush(Message.Data.newBuilder().setDataType(Message.Data.DataType.Ack).setAck(Message.Ack.newBuilder().setTo(logout.getForm()).setAckStatus(Message.Ack.AckStatus.Receive).build()).build());
    }

    private static void dealChatMsg(Message.Data data, Channel channel) {
        Message.Chat chat = data.getChat();
        validateToken(chat);
        MessageHandler.sendMsg(data);
        channel.writeAndFlush(Message.Data.newBuilder().setDataType(Message.Data.DataType.Ack).setAck(Message.Ack.newBuilder().setTo(chat.getForm()).setMsgId(chat.getId()).setAckStatus(Message.Ack.AckStatus.Receive).build()).build());
    }

    private static void dealPingMsg(Channel channel) {
        NettyAttrUtil.updateReaderTime(channel, System.currentTimeMillis() + Constant.PING_ADD_TIME);
    }

    private static void dealLoginMsg(Message.Data data, Channel channel) {
        Message.Login login = data.getLogin();
        validateUser(login);
        Session session = Session.builder().channel(channel)
                .userId(login.getForm())
                .buildTime(new Date())
                .build();
        SessionManager.putSession(login.getForm(), session);
        incrementOnLineUser(channel);
        MessageHandler.sendMsg(data);
        channel.writeAndFlush(Message.Data.newBuilder().setDataType(Message.Data.DataType.Ack).setAck(Message.Ack.newBuilder().setTo(login.getForm()).setAckStatus(Message.Ack.AckStatus.Receive).build()).build());
    }

    private static void validateUser(Message.Login login) {
        String token = RedisPoolUtil.get(Constant.SYSTEM_PREFIX + Constant.USER_TOKEN_KEY + login.getForm());
        if (StringUtils.isEmpty(token)) {
            throw new UserException();
        }
    }

    private static void validateToken(Message.Chat chat) {
        Long time = RedisPoolUtil.ttl(Constant.SYSTEM_PREFIX + Constant.USER_TOKEN_KEY + chat.getForm());
        if (time <= 0) {
            throw new UserException();
        }
    }

    private static synchronized void incrementOnLineUser(Channel channel) {
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
