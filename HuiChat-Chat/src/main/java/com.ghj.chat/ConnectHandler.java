package com.ghj.chat;

import com.ghj.chat.message.MessageManager;
import com.ghj.common.base.Code;
import com.ghj.common.base.Constant;
import com.ghj.common.exception.UserException;
import com.ghj.common.util.*;
import com.ghj.protocol.Message;
import com.ghj.protocol.MessageProto;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.RetryNTimes;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Objects;

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
        Message.Data ackMessage;
        switch (data.getDataType()) {
            case Login:
                try {
                    Message.Login login = data.getLogin();
                    validateUser(login);
                    NettyAttrUtil.updateReaderTime(channel, System.currentTimeMillis() + Constant.PING_ADD_TIME);
                    Session session = Session.builder().channel(channel)
                            .userId(login.getForm())
                            .build();
                    SessionManager.putSession(login.getForm(), session);
                    incrementOnLineUser(channel);
                    ackMessage = Message.Data.newBuilder().setDataType(Message.Data.DataType.Ack).setAck(Message.Ack.newBuilder().setMsgId(login.getForm()).build()).build();
                } catch (Exception e) {

                }
                MessageManager.getInstance().putMessage(ackMessage);
                break;
            case Ping:
                try {
                    NettyAttrUtil.updateReaderTime(channel, System.currentTimeMillis() + Constant.PING_ADD_TIME);
                } catch (Exception e) {
                }
                break;
            case Chat:
            case Logout:
                try {
                    SessionManager.removeSession(message.getFromUserId());
                } catch (Exception e) {
                }
                MessageManager.getInstance().putMessage(ackMessage);
                break;
            case UNRECOGNIZED:
                default:
        }
    }

    public void validateUser(Message.Login login) {
        String token = RedisPoolUtil.get(Constant.SYSTEM_PREFIX  + Constant.USER_TOKEN_KEY + login.getForm());
        if (StringUtils.isEmpty(token)) {
            throw new UserException();
        }
    }

    public synchronized void incrementOnLineUser(Channel channel) throws Exception {
        String connect = PropertiesUtil.getInstance().getValue(Constant.ZOOKEEPER_CONNECT, "127.0.0.1:2181");
        CuratorFramework client = ZookeeperUtil.getInstance(connect);
        InetSocketAddress inetAddress = (InetSocketAddress) channel.localAddress();
        String path = Constant.SERVER_NODE + inetAddress.getAddress() + ":" + inetAddress.getPort();
        DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(client, path, new RetryNTimes(3, 1000));
        atomicInteger.add(1);
    }




}
