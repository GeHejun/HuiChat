package com.ghj.chat;


import com.ghj.common.netty.Connector;
import com.ghj.common.netty.Register;
import com.ghj.common.netty.ServerChannelGenerator;
import com.ghj.protocol.MessageProto;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @author GeHejun
 * @date 2019-06-24
 */
public class ServerConnector implements Connector {

    private NioEventLoopGroup bossGroup = new NioEventLoopGroup();
    private NioEventLoopGroup workerGroup = new NioEventLoopGroup();
    private int port;
    Channel channel;

    @Override
    public void start(int port) {
        this.port = port;
        channel = ServerChannelGenerator.generateChannel(bossGroup, workerGroup, port,
                MessageProto.Message.ConnectType.NETTY, new ConnectHandler(), ()->{
                    new Register().register(this, MessageProto.Message.ConnectType.NETTY, MessageProto.Message.MessageBehavior.SERVER_REGISTER);
                });
    }

    @Override
    public void stop() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    @Override
    public int getPort() {
        return port;
    }
}


