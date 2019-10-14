package com.ghj.androidsdk;

import com.ghj.protocol.Message;

/**
 * @author gehj
 * @version 1.0
 * @description GeHejun
 * @date 2019/10/14 15:30
 */
public class MessageSender {

    public void sendMessage(Message.Chat message) {
        ClientStarter.getChannel().writeAndFlush(message);
    }
}
