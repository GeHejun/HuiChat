package com.ghj.androidsdk;

import io.netty.channel.Channel;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/14 13:14
 */
public class ClientStarter {

    private static Channel channel;

    public static void start() {
        ClientConnector clientConnector = new ClientConnector();
        try {
            channel = clientConnector.start();
        } catch (Exception e) {
            e.printStackTrace();
            clientConnector.stop();
        }
    }

    public static Channel getChannel() {
        return channel;
    }
}
