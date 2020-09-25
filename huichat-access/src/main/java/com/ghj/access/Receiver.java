package com.ghj.access;

import com.ghj.protocol.Message;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class Receiver {

    @Value("${}")
    private int port;

    private final NioEventLoopGroup bossGroup = new NioEventLoopGroup();
    private final NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    @PostConstruct
    public void start() {
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
                                    .addLast(new ProtobufDecoder(Message.Data.getDefaultInstance()))
                                    .addLast(new ReceiveHandler());
                        }
                    });
            ChannelFuture future = serverBootstrap.bind(port).sync();
            if (future.isSuccess()) {
                log.info("服务端启动成功...");
                //TODO 注册
                //register(future);
            } else {
                log.info("服务端启动失败，失败原因：{}", future.cause().getMessage());
                throw new RuntimeException(future.cause());
            }
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("服务端启动异常，具体原因：{}", e.getMessage());
            //stop();
        }
    }
}
