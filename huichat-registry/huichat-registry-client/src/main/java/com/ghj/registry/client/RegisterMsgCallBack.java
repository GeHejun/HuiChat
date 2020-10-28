package com.ghj.registry.client;

import com.ghj.common.callback.MsgCallBack;
import com.ghj.protocol.Msg;
import lombok.extern.slf4j.Slf4j;

import static com.ghj.protocol.Msg.SysMsg.Module.REGISTRY;

@Slf4j
public abstract class RegisterMsgCallBack extends MsgCallBack {
    @Override
    public void doBack(Msg.Data data) {
        Msg.SysMsg sysMsg = data.getSysMsg();
        if (REGISTRY == sysMsg.getFromM()) {
            callBack(sysMsg);
        }
    }

    public abstract void callBack(Msg.SysMsg sysMsg);
}
