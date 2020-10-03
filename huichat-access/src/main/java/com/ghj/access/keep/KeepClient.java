package com.ghj.access.keep;

import com.ghj.protocol.Msg;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class KeepClient implements Runnable {

    private final String host;

    private final int port;

    private Channel keepClientChannel;

    KeepClientHandler keepClientHandler = new KeepClientHandler();

    EventLoopGroup group = new NioEventLoopGroup();


    public KeepClient(String host, int port) {
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
                    .handler(keepClientHandler);
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            //注册连接事件
            channelFuture.addListener((ChannelFutureListener) future -> {
                //如果连接成功
                if (future.isSuccess()) {
                    log.info("客户端[" + channelFuture.channel().localAddress().toString() + "]已连接...");
                    keepClientChannel = channelFuture.channel();
                }
                //如果连接失败，尝试重新连接
                else{
                    log.info("客户端[" + channelFuture.channel().localAddress().toString() + "]连接失败，重新连接中...");
                    future.channel().close();
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
        } finally {
            group.shutdownGracefully();
        }
    }

    public void sendMsg(Msg.Data data) {
        keepClientHandler.sendMsg(data);
    }

    public void sendMsg(Msg.Data data, List<MsgCallBack> msgCallBacks) {
        keepClientHandler.sendMsg(data);
        keepClientHandler.setMsgCallBacks(msgCallBacks);
    }

    /**
     * 客户端关闭
     */
    private void close() {
        //关闭客户端套接字
        if(keepClientChannel!=null){
            keepClientChannel.close();
        }
        //关闭客户端线程组
        if (group != null) {
            group.shutdownGracefully();
        }
    }

}
