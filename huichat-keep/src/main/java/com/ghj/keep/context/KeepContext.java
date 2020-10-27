package com.ghj.keep.context;

import com.ghj.keep.keep.ServerState;
import org.springframework.stereotype.Component;

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
