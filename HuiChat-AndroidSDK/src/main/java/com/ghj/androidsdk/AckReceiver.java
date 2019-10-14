package com.ghj.androidsdk;

import com.ghj.protocol.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/14 15:35
 */
public class AckReceiver implements MessageReceiver {

    ConcurrentHashMap<Long, List<Message.Ack>> ackReceiveMap = new ConcurrentHashMap();

    @Override
    public void dealMessage(Message.Data data) {
        Long msgId = data.getAck().getMsgId();
        if (ackReceiveMap.containsKey(msgId)) {
            List<Message.Ack> ackList = ackReceiveMap.get(msgId);
            ackList.add(data.getAck());
        } else {
            List<Message.Ack> ackList = new ArrayList<>();
            ackList.add(data.getAck());
            ackReceiveMap.put(msgId, ackList);
        }
    }


}
