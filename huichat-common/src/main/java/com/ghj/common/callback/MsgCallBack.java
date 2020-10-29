package com.ghj.common.callback;

import com.ghj.protocol.Msg;
import com.google.common.collect.Lists;

import java.util.List;

import static com.ghj.protocol.Msg.Data.DataType.SYS_MSG;
import static com.ghj.protocol.Msg.SysMsg.MsgType.ACK;

public abstract class MsgCallBack {

    private final List<Long> needCallBackMsgIds = Lists.newArrayList();

    public void addNeedCallBackMsg(Long id) {
        needCallBackMsgIds.add(id);
    }

    public void back(Msg.Data data) {
        //这里可以不用判断的，已经判断过一次了
        if (SYS_MSG == data.getDataType() && ACK == data.getSysMsg().getMsgType()) {
            long ackMsgId = data.getSysMsg().getAck().getAckMsgId();
            if (needCallBackMsgIds.contains(ackMsgId)) {
                doBack(data);
                removeBackedMsg(ackMsgId);
            }
        }
    }

    public abstract void doBack(Msg.Data data);

    void removeBackedMsg(Long id) {
        needCallBackMsgIds.remove(id);
    }
}
