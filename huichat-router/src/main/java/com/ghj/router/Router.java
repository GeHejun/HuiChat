package com.ghj.router;

import com.ghj.protocol.Msg;
import com.ghj.router.config.Config;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
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
public class Router {
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
    private RouteHandler routeHandler;
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
                            .addLast(routeHandler);
                }
            });
            ChannelFuture feature = serverBootstrap.bind(port).sync();
            feature.addListener( future -> {
                //如果连接成功
                if (future.isSuccess()) {
                    //TODO 注册以后抽出公共客户端放到registry模块中，别的模块调用API即可
                    register();
                }
            });
            feature.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("服务器启动失败", e);
            boss.shutdownGracefully();
            work.shutdownGracefully();
            Thread.currentThread().interrupt();
        }
    }

    public void register() {
//        int port = config.getRegistryServerPort();
//        String host = config.getRegistryServerHost();
//        KeepClient keepClient = new KeepClient(host, port);
//        long msgId = new SnowFlakeIdGenerator(WorkIdGenerator.getWorkId(), DataCenterIdGenerator.getDataCenterId()).nextId();
//        Msg.SysMsg.Register register = Msg.SysMsg.Register.newBuilder().setPort(config.getKeepServerPort()).build();
//        Msg.SysMsg sysMsg = Msg.SysMsg.newBuilder()
//                .setMsgType(Msg.SysMsg.MsgType.REGISTER)
//                .setId(msgId)
//                .setFromM(Msg.SysMsg.Module.ACCESS)
//                .setToM(Msg.SysMsg.Module.REGISTRY)
//                .setTimestamp(System.currentTimeMillis())
//                .setRegister(register)
//                .build();
//        Msg.Data data = Msg.Data.newBuilder()
//                .setDataType(Msg.Data.DataType.SYS_MSG)
//                .setSysMsg(sysMsg)
//                .build();
//        RegisterMsgCallBack registerMsgCallBack = new RegisterMsgCallBack();
//        registerMsgCallBack.addNeedCallBackMsg(msgId);
//        List<MsgCallBack> msgCallBacks = Lists.newArrayList(registerMsgCallBack);
//        keepClient.sendMsg(data, msgCallBacks);
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