package com.ghj.keep.client;

import com.ghj.keep.context.KeepContext;
import com.ghj.protocol.Msg;
import com.ghj.registry.client.RegisterMsgCallBackHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KeepRegisterMsgCallBackHandler implements RegisterMsgCallBackHandler {
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
