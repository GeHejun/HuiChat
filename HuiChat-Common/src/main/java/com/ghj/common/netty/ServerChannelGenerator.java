package com.ghj.common.netty;

import com.ghj.protocol.MessageProto;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/8/27 13:47
 */
public class ServerChannelGenerator {

    public static Channel generateChannel(NioEventLoopGroup bossGroup, NioEventLoopGroup workerGroup,
                                          int port, MessageProto.Message.ConnectType connectType,
                                          SimpleChannelInboundHandler handler, CallBack callBack) {
        Channel channel = null;
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO));
            if (connectType == MessageProto.Message.ConnectType.WEBSOCKET) {
                serverBootstrap.childHandler(new WebSocketChannelInitializer(handler));
            }
            if (connectType == MessageProto.Message.ConnectType.NETTY) {
                serverBootstrap.childHandler(new NettyChannelInitializer(handler));
            }
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.addListener(future -> callBack.call());
            channel = channelFuture.channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
        return channel;
    }
}
