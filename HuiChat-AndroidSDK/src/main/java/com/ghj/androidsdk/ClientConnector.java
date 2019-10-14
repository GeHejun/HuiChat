package com.ghj.androidsdk;

import com.ghj.protocol.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
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

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/14 13:15
 */
public class ClientConnector {

    private NioEventLoopGroup group = new NioEventLoopGroup();

    public Channel start() {
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline()
                                    .addLast(new ProtobufEncoder())
                                    .addLast(new ProtobufDecoder(Message.Data.getDefaultInstance()))
                                    .addLast(new ConnectHandler());
                        }
                    });
            String[] node = RoutingStrategy.findBestServer();
            ChannelFuture future = bootstrap.connect(node[0], Integer.parseInt(node[1])).sync();
            if (future.isSuccess()) {
                return future.channel();
            } else {
                future.cause().printStackTrace();
                future.channel().closeFuture().sync();
                throw new RuntimeException(future.cause());
            }
        } catch (Exception e) {
            e.printStackTrace();
            stop();
            throw new RuntimeException();
        }
    }

    public void stop() {
        group.shutdownGracefully();
    }

}
