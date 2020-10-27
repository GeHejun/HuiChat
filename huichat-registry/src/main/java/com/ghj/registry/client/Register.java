package com.ghj.registry.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Register implements Runnable {

    private final String host;

    private final int port;

    private Channel registerChannel;

    RegisterHandler registerHandler = new RegisterHandler();

    EventLoopGroup group = new NioEventLoopGroup();


    public Register(String host, int port) {
        this.host = host;
        this.port = port;
    }


    @Override
    public void run() {
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(registerHandler);
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            //注册连接事件
            channelFuture.addListener(future -> {
                //如果连接成功
                if (future.isSuccess()) {
                    log.info("客户端[" + channelFuture.channel().localAddress().toString() + "]已连接...");
                    registerChannel = channelFuture.channel();
                }
                //如果连接失败，尝试重新连接
                else{
                    log.info("客户端[" + channelFuture.channel().localAddress().toString() + "]连接失败，重新连接中...");
                    bootstrap.connect(host, port);
                }
            });
            //注册关闭事件
            channelFuture.channel().closeFuture().addListener(cfl -> {
                close();
                log.info("客户端[" + channelFuture.channel().localAddress().toString() + "]已断开...");
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            group.shutdownGracefully();
        }
    }


    /**
     * 客户端关闭
     */
    private void close() {
        //关闭客户端套接字
        if(registerChannel!=null){
            registerChannel.close();
        }
        //关闭客户端线程组
        if (group != null) {
            group.shutdownGracefully();
        }
    }
}
