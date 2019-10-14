package com.ghj.androidsdk;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/14 16:03
 */
public class MessageManager {

    private static MessageManager messageManager = null;

    public static MessageManager getInstance() {
        if (messageManager == null) {
            synchronized (MessageManager.class) {
                if (messageManager == null) {
                    messageManager = new MessageManager();
                }
            }
        }
        return messageManager;
    }

    private  ConcurrentHashMap<Long, Message> messageMap = new ConcurrentHashMap<>();

    private  CallBack callBack;


    public  void invalidMessage() {
        for (;;) {
            messageMap.forEach((k,v) -> {
                if (System.currentTimeMillis() - v.getInvalidTime() > 0) {
                    callBack.dealInvalidateMessage(v.getChat());
                    messageMap.remove(v);
                }
            });
        }
    }

    public  void dealAckMessage(com.ghj.protocol.Message.Ack ack) {
        if (messageMap.containsKey(ack.getMsgId())) {
            if (com.ghj.protocol.Message.Ack.AckStatus.Read == ack.getAckStatus()) {
                callBack.dealReadMessage(ack);
            }
        }
    }

    interface CallBack {
        /**
         * 处理过期消息
         * @param chat
         */
       void dealInvalidateMessage(com.ghj.protocol.Message.Chat chat);

       void dealReadMessage(com.ghj.protocol.Message.Ack ack);
    }
}
