package com.ghj.access.keep;

import com.ghj.access.config.Config;
import com.ghj.common.util.ThreadPoolManager;
import com.ghj.protocol.Msg;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

@Component
@Slf4j
public class Keeper {
    /**
     * 创建bootstrap
     */
    ServerBootstrap serverBootstrap = new ServerBootstrap();
    /**
     * BOSS
     */
    EventLoopGroup boss = new NioEventLoopGroup();
    /**
     * Worker
     */
    EventLoopGroup work = new NioEventLoopGroup();
    /**
     * 通道适配器
     */
    @Resource
    private KeepHandler keepHandler;
    /**
     * NETT服务器配置类
     */
    @Resource
    private Config config;


    /**
     * 开启及服务线程
     */
    @PostConstruct
    public void start() {
        startServer();
    }

    private void startServer() {
        int port = config.getKeepServerPort();
        serverBootstrap.group(boss, work)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.INFO));
        try {
            //设置事件处理
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline()
                            .addLast(new ProtobufEncoder())
                            .addLast(new ProtobufDecoder(Msg.Data.getDefaultInstance()))
                            .addLast(keepHandler);
                }
            });
            ChannelFuture feature = serverBootstrap.bind(port).sync();
            feature.addListener((ChannelFutureListener) future -> {
                //如果连接成功
                if (future.isSuccess()) {
                    startClient();
                }
            });
            feature.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    private void startClient() {
        int port = config.getRegistryServerPort();
        String host = config.getRegistryServerHost();
        //连接注册中心
        KeepClient keepClient = new KeepClient(host, port);
        //注册信息
        Msg.Data data = Msg.Data.newBuilder().setDataType(Msg.Data.DataType.REGISTER).build();
        Msg.Register register = Msg.Register.newBuilder().setPort(port).build();
        //获取router列表
        keepClient.sendMsg(data);
        //遍历列表获取router配置

        //连接
    }

    /**
     * 关闭服务器方法
     */
    @PreDestroy
    public void close() {
        //优雅退出
        boss.shutdownGracefully();
        work.shutdownGracefully();
    }
}
