package com.ghj.androidsdk;

import com.ghj.common.base.Constant;

import java.util.List;
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

    private  ConcurrentHashMap<Long, ChatMessage> messageMap = new ConcurrentHashMap<>();

    public void putMessage(ChatMessage chatMessage) {
        messageMap.put(chatMessage.getChat().getId(), chatMessage);
    }

    private List<CallBack> invalidMessageCallBacks;


    private List<CallBack> readMessageCallBacks;


    public  void invalidMessage() {
        for (;;) {
            messageMap.forEach((k,v) -> {
                if (System.currentTimeMillis() - v.getInvalidTime() > 0) {
                    invalidMessageCallBacks.forEach(callBack -> {
                        callBack.dealInvalidateMessage(v.getChat());
                        messageMap.remove(k);
                    });

                }
            });
        }
    }

    public  void dealAckMessage(com.ghj.protocol.Message.Ack ack) {
        if (messageMap.containsKey(ack.getMsgId())) {
            if (com.ghj.protocol.Message.Ack.AckStatus.Read == ack.getAckStatus()) {
                readMessageCallBacks.forEach(callBack -> {
                    callBack.dealReadMessage(ack);
                    messageMap.remove(ack.getMsgId());
                });

            } else {
                ChatMessage chatMessage = messageMap.get(ack.getMsgId());
                chatMessage.setInvalidTime(chatMessage.getInvalidTime() + Constant.MESSAGE_TIMEOUT_ADD);
            }
        }
    }

    interface CallBack {
        /**
         * 处理过期消息
         * @param chat
         */
       void dealInvalidateMessage(com.ghj.protocol.Message.Chat chat);

        /**
         * 处理已读的ack消息
         * @param ack
         */
       void dealReadMessage(com.ghj.protocol.Message.Ack ack);
    }
}
