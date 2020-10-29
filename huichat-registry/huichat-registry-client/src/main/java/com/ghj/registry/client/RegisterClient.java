package com.ghj.registry.client;


public class RegisterClient {

    public static void register(String host, int port, int thisServerPort, RoutingMsgHandler routingMsgHandler, RegisterMsgCallBackHandler registerMsgCallBackHandler) {
        Register register = new Register(host, port, thisServerPort);
        register.setRoutingMsgHandler(routingMsgHandler);
        register.setRegisterMsgCallBackHandler(registerMsgCallBackHandler);
        new Thread(register).start();
    }
}
