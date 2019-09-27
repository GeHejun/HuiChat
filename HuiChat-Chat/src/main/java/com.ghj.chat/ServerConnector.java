package com.ghj.chat;


import com.alibaba.fastjson.JSON;
import com.ghj.common.base.Constant;
import com.ghj.common.util.PropertiesUtil;
import com.ghj.common.util.ServerNode;
import com.ghj.common.util.ZookeeperUtil;
import com.ghj.protocol.MessageProto;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author GeHejun
 * @date 2019-06-24
 */
public class ServerConnector {

    private NioEventLoopGroup bossGroup = new NioEventLoopGroup();
    private NioEventLoopGroup workerGroup = new NioEventLoopGroup();
    private static final int SESSION_TIMEOUT = 5000;

    public void start(int port) {
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline()
                                    .addLast(new ProtobufEncoder())
                                    .addLast(new ProtobufDecoder(MessageProto.Message.getDefaultInstance()))
                                    .addLast(new ConnectHandler());
                        }
                    });
            ChannelFuture future = serverBootstrap.bind(port).sync();
            if (future.isSuccess()) {
                register(future);
            } else {
                future.cause().printStackTrace();
                throw new RuntimeException(future.cause());
            }
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
    }

    public void stop() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    private void register(ChannelFuture future) {
        AtomicInteger retryCount = new AtomicInteger(3);
        String connect = PropertiesUtil.getInstance().getValue(Constant.ZOOKEEPER_CONNECT, "");
        InetSocketAddress inetSocketAddress = (InetSocketAddress) future.channel().localAddress();
        ServerNode serverNode = new ServerNode(inetSocketAddress.getHostString(), inetSocketAddress.getPort());
        while (!reRegister(connect, JSON.toJSONString(serverNode)) && retryCount.intValue() > 0) {
            retryCount.getAndDecrement();
        }
    }

    private boolean reRegister(String connect, String serverNodeString) {
        return new ZookeeperUtil(watchedEvent -> {
        }, connect, SESSION_TIMEOUT).addZEnode(Constant.SERVER_NODE, serverNodeString);
    }

}


