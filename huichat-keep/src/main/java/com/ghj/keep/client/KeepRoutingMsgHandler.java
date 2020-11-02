package com.ghj.keep.client;

import com.ghj.keep.context.KeepClientContext;
import com.ghj.protocol.Msg;
import com.ghj.registry.client.RoutingMsgHandler;


public class KeepRoutingMsgHandler implements RoutingMsgHandler {
    @Override
    public void dealRoutingMsg(Msg.SysMsg.Routing routing) {
        routing.getAddressList().forEach(KeepClientContext::adjustKeepClient);
    }
}
