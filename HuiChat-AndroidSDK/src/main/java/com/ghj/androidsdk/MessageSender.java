package com.ghj.androidsdk;

import com.ghj.common.base.Constant;
import com.ghj.protocol.Message;

/**
 * @author gehj
 * @version 1.0
 * @description GeHejun
 * @date 2019/10/14 15:30
 */
public class MessageSender {

    public void sendMessage(Message.Chat chat) {
        ClientStarter.getChannel().writeAndFlush(chat);
        com.ghj.androidsdk.Message message = new com.ghj.androidsdk.Message();
        message.setChat(chat);
        message.setInvalidTime(System.currentTimeMillis() + Constant.MESSAGE_TIMEOUT);
        MessageManager.getInstance().putMessage(message);
    }
}
