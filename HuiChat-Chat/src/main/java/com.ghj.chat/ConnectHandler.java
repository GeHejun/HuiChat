package com.ghj.chat;

import com.ghj.chat.message.MessageManager;
import com.ghj.common.base.Code;
import com.ghj.common.base.Constant;
import com.ghj.common.exception.UserException;
import com.ghj.common.util.*;
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
        MessageProto.Message message = (MessageProto.Message)o;
        Channel channel = channelHandlerContext.channel();
        MessageProto.Message ackMessage;
        switch (message.getMessageBehavior()) {
            case LOGIN:
                try {
                    validateUser(message);
                    NettyAttrUtil.updateReaderTime(channel, System.currentTimeMillis() + Constant.PING_ADD_TIME);
                    Session session = Session.builder().channel(channel)
                            .userId(message.getFromUserId())
                            .build();
                    SessionManager.putSession(message.getFromUserId(), session);
                    incrementOnLineUser(channel,message);
                    ackMessage = buildAckMessage(Code.LOGIN_SUCCESS, message);
                } catch (Exception e) {
                    ackMessage = buildAckMessage(Code.LOGIN_FAILURE, message);
                    if (Objects.isNull(SessionManager.getSession(message.getFromUserId()))) {
                        channel.writeAndFlush(ackMessage);
                        break;
                    }
                }
                MessageManager.getInstance().putMessage(ackMessage);
                break;
            case PING:
                try {
                    NettyAttrUtil.updateReaderTime(channel, System.currentTimeMillis() + Constant.PING_ADD_TIME);
                    MessageManager.getInstance().putMessage(message);
                    ackMessage = buildAckMessage(Code.PING_SUCCESS, message);
                } catch (Exception e) {
                    ackMessage = buildAckMessage(Code.PING_FAILURE, message);
                }
                MessageManager.getInstance().putMessage(ackMessage);
                break;
            case MESSAGE:
            case VALIDATION_MESSAGE:
            case REPLY_VALIDATION_MESSAGE:
                try {
                    MessageManager.getInstance().putMessage(message);
                    ackMessage = buildAckMessage(Code.MESSAGE_SEND_SUCCESS, message);
                } catch (Exception e) {
                    ackMessage = buildAckMessage(Code.MESSAGE_SEND_FAILURE, message);
                }
                MessageManager.getInstance().putMessage(ackMessage);
                break;
            case LOGIN_OUT:
                try {
                    SessionManager.removeSession(message.getFromUserId());
                    ackMessage = buildAckMessage(Code.LOGIN_OUT_SUCCESS, message);
                } catch (Exception e) {
                    ackMessage = buildAckMessage(Code.LOGIN_OUT_FAILURE, message);
                }
                MessageManager.getInstance().putMessage(ackMessage);
                break;
            case ACK:
                break;
            case UNRECOGNIZED:
                default:
        }
    }

    public void validateUser(MessageProto.Message message) {
        String token = RedisPoolUtil.get(Constant.SYSTEM_PREFIX  + Constant.USER_TOKEN_KEY + message.getFromUserId());
        if (StringUtils.isEmpty(token)) {
            throw new UserException();
        }
        if (!token.equals(message.getToken())) {
            throw new UserException();
        }

    }

    public synchronized void incrementOnLineUser(Channel channel, MessageProto.Message message) throws Exception {
        InetSocketAddress socketAddress = (InetSocketAddress)channel.localAddress();
        RedisPoolUtil.lpush(Constant.ON_LINE_USER_LIST, String.valueOf(message.getFromUserId()));
        RedisPoolUtil.increment(Constant.ON_LINE_USER_COUNT+"_"+socketAddress.getAddress()+"_"+socketAddress.getPort());
        String connect = PropertiesUtil.getInstance().getValue(Constant.ZOOKEEPER_CONNECT, "127.0.0.1:2181");
        CuratorFramework client = ZookeeperUtil.getInstance(connect);
        InetSocketAddress inetAddress = (InetSocketAddress) channel.localAddress();
        String path = Constant.SERVER_NODE + inetAddress.getAddress() + ":" + inetAddress.getPort();
        DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(client, path, new RetryNTimes(3, 1000));
        atomicInteger.add(1);
        ZookeeperUtil.updateNode(client, path,  atomicInteger.get().postValue().toString());
    }

    public MessageProto.Message buildAckMessage(Code code, MessageProto.Message message) {
        return MessageProto.Message.newBuilder()
                .setCode(code.getCode())
                .setContent(code.getMessage())
                .setId(new SnowFlakeIdGenerator(message.getDeviceId(), MachineSerialNumber.get()).nextId())
                .setToUserId(message.getFromUserId())
                .setMatchMessageId(message.getId())
                .setMessageBehavior(MessageProto.Message.MessageBehavior.ACK)
                .build();
    }



}
