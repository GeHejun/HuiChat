package com.ghj.registry.client;

import com.ghj.protocol.Msg;

public interface RoutingMsgHandler {
    void dealRoutingMsg(Msg.SysMsg.Routing routing);
}
