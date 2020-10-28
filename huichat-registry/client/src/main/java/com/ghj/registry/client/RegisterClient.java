package com.ghj.registry.client;


public class RegisterClient {

    public static void register(String host, int port, RoutingMsgHandler routingMsgHandler) {
        Register register = new Register(host, port);
        register.setRoutingMsgHandler(routingMsgHandler);
        new Thread(register).start();
    }
}
