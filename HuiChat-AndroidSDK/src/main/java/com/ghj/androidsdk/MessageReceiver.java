package com.ghj.androidsdk;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/14 19:40
 */
public class MessageReceiver {

    CallBack callBack;

    public void receiveMessage(Message.Chat chat) {
        callBack.dealChatMessage(chat);
    }

    interface CallBack {
        /**
         * 处理聊天消息
         * @param chat
         */
        void dealChatMessage(com.ghj.protocol.Message.Chat chat);
    }
}
