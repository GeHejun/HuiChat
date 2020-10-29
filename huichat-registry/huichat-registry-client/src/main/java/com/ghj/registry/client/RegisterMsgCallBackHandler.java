package com.ghj.registry.client;

import com.ghj.protocol.Msg;

public interface RegisterMsgCallBackHandler {
    void callBack(Msg.SysMsg sysMsg);
}
