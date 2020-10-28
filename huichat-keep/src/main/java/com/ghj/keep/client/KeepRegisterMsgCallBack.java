package com.ghj.keep.client;

import com.ghj.keep.context.KeepContext;
import com.ghj.protocol.Msg;
import com.ghj.registry.client.RegisterMsgCallBack;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KeepRegisterMsgCallBack extends RegisterMsgCallBack {
    @Override
    public void callBack(Msg.SysMsg sysMsg) {
        Msg.SysMsg.Ack ack = sysMsg.getAck();
        if (200 == ack.getCode()) {
            KeepContext.enableServer();
        } else {
            log.error("注册服务器异常:{}", ack.getMessage());
        }
    }
}
