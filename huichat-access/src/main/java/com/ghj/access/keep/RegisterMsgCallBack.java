package com.ghj.access.keep;

import com.ghj.protocol.Msg;
import lombok.extern.slf4j.Slf4j;

import static com.ghj.protocol.Msg.SysMsg.Module.REGISTRY;

@Slf4j
public class RegisterMsgCallBack extends MsgCallBack {
    @Override
    void doBack(Msg.Data data) {
        Msg.SysMsg sysMsg = data.getSysMsg();
        if (REGISTRY == sysMsg.getFromM()) {
            Msg.SysMsg.Ack ack = sysMsg.getAck();
            if (200 == ack.getCode()) {
                KeepContext.enableServer();
            } else {
                log.error("注册服务器异常:{}", ack.getMessage());
            }
        }
    }
}
