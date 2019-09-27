package com.ghj.chat;

/**
 * @author gehj
 * @date 2019/6/2414:26
 */
public class ServerStarter {

    public static void main(String[] args) {
        ServerConnector serverConnector = new ServerConnector();
        try {
            serverConnector.start(8999);
        } catch (Exception e) {
            e.printStackTrace();
            serverConnector.stop();
        }
    }

}
