package com.ghj.access.keep;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class KeepContext {

    private KeepContext() {}

    private static ServerState serverState = ServerState.NOT_ENABLE;

    public static synchronized void enableServer() {
        serverState = ServerState.ENABLE;
    }

    public static synchronized Boolean checkServer() {
        return ServerState.ENABLE == serverState;
    }

}
