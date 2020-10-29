package com.ghj.registry.client;

import com.ghj.common.callback.MsgCallBack;
import com.ghj.protocol.Msg;
import lombok.extern.slf4j.Slf4j;

import static com.ghj.protocol.Msg.SysMsg.Module.REGISTRY;

@Slf4j
public class RegisterMsgCallBack extends MsgCallBack {

    RegisterMsgCallBackHandler registerMsgCallBackHandler;

    public void setRegisterMsgCallBackHandler(RegisterMsgCallBackHandler registerMsgCallBackHandler) {
        this.registerMsgCallBackHandler = registerMsgCallBackHandler;
    }

    @Override
    public void doBack(Msg.Data data) {
        Msg.SysMsg sysMsg = data.getSysMsg();
        if (REGISTRY == sysMsg.getFromM()) {
            registerMsgCallBackHandler.callBack(sysMsg);
        }
    }
}
