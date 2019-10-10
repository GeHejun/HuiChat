package com.ghj.chat;


import com.ghj.common.base.Constant;
import com.ghj.common.util.PropertiesUtil;
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
import org.apache.curator.framework.CuratorFramework;

import java.net.InetSocketAddress;

/**
 * @author GeHejun
 * @date 2019-06-24
 */
public class ServerConnector {

    private NioEventLoopGroup bossGroup = new NioEventLoopGroup();
    private NioEventLoopGroup workerGroup = new NioEventLoopGroup();


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
        String connect = PropertiesUtil.getInstance().getValue(Constant.ZOOKEEPER_CONNECT, "127.0.0.1:2181");
        InetSocketAddress inetSocketAddress = (InetSocketAddress) future.channel().localAddress();
        CuratorFramework client = ZookeeperUtil.getInstance(connect);
        ZookeeperUtil.createNode(client, Constant.SERVER_NODE + inetSocketAddress.getAddress() + ":" + inetSocketAddress.getPort(), Constant.DEFAULT_CONNECT_NUMBER);
    }

}


