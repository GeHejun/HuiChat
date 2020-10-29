package com.ghj.registry.client;

import com.ghj.common.util.DataCenterIdGenerator;
import com.ghj.common.util.SnowFlakeIdGenerator;
import com.ghj.common.util.WorkIdGenerator;
import com.ghj.protocol.Msg;
import com.google.common.collect.Lists;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Register implements Runnable {

    private final String host;

    private final int port;

    private final int thisServerPort;

    private Channel registerChannel;

    private RegisterHandler registerHandler;

    private RegisterMsgCallBackHandler registerMsgCallBackHandler;

    EventLoopGroup group = new NioEventLoopGroup();


    public Register(String host, int port, int thisServerPort) {
        this.host = host;
        this.port = port;
        this.thisServerPort = thisServerPort;
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
            sendRegisterMsg();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            group.shutdownGracefully();
        }
    }

    private void sendRegisterMsg() {
        long msgId = new SnowFlakeIdGenerator(WorkIdGenerator.getWorkId(), DataCenterIdGenerator.getDataCenterId()).nextId();
        Msg.SysMsg.Register register = Msg.SysMsg.Register.newBuilder().setPort(thisServerPort).build();
        Msg.SysMsg sysMsg = Msg.SysMsg.newBuilder()
                .setMsgType(Msg.SysMsg.MsgType.REGISTER)
                .setId(msgId)
                .setFromM(Msg.SysMsg.Module.ACCESS)
                .setToM(Msg.SysMsg.Module.REGISTRY)
                .setTimestamp(System.currentTimeMillis())
                .setRegister(register)
                .build();
        Msg.Data data = Msg.Data.newBuilder()
                .setDataType(Msg.Data.DataType.SYS_MSG)
                .setSysMsg(sysMsg)
                .build();
        RegisterMsgCallBack registerMsgCallBack = new RegisterMsgCallBack();
        registerMsgCallBack.setRegisterMsgCallBackHandler(registerMsgCallBackHandler);
        registerMsgCallBack.addNeedCallBackMsg(sysMsg.getId());
        registerHandler.sendMsg(data, Lists.newArrayList(registerMsgCallBack));
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

    public void setRoutingMsgHandler(RoutingMsgHandler routingMsgHandler) {
        registerHandler = new RegisterHandler(routingMsgHandler);
    }


    public void setRegisterMsgCallBackHandler(RegisterMsgCallBackHandler registerMsgCallBackHandler) {
        this.registerMsgCallBackHandler = registerMsgCallBackHandler;
    }
}
