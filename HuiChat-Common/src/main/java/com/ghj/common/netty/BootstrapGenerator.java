package com.ghj.common.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/8/27 13:47
 */
public class BootstrapGenerator {

    public static Bootstrap generateBootStrap(SimpleChannelInboundHandler handler) {
        Bootstrap client = new Bootstrap();
        //第1步 定义线程组，处理读写和链接事件，没有了accept事件
        EventLoopGroup group = new NioEventLoopGroup();
        client.group(group);

        //第2步 绑定客户端通道
        client.channel(NioSocketChannel.class);

        //第3步 给NIoSocketChannel初始化handler， 处理读写事件
        client.handler(new NettyChannelInitializer(handler));
        return client;
    }
}
