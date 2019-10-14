package com.ghj.androidsdk;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/14 13:14
 */
public class ClientStarter {
    public static void main(String[] args) {
        ClientConnector clientConnector = new ClientConnector();
        try {
            clientConnector.start();
        } catch (Exception e) {
            e.printStackTrace();
            clientConnector.stop();
        }
    }
}
