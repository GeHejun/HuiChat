package com.ghj.androidsdk;

import com.ghj.common.base.Constant;
import com.ghj.common.util.SnowFlakeIdGenerator;
import com.ghj.protocol.Message;

/**
 * @author gehj
 * @version 1.0
 * @description GeHejun
 * @date 2019/10/14 15:30
 */
public class MessageSender {

    public static Long sendMessage(String content, Integer type, Boolean isGroup, Integer from, Integer to) {
        long id = new SnowFlakeIdGenerator(1, 1).nextId();
        Message.Chat chat = Message.Chat.newBuilder().setChatType(isGroup ? Message.Chat.ChatType.Group : Message.Chat.ChatType.Single).setContent(content).setForm(from).setTo(to).setType(type).setId(id).build();
        ClientStarter.getChannel().writeAndFlush(chat);
        ChatMessage message = ChatMessage.builder().chat(chat).invalidTime(System.currentTimeMillis() + Constant.MESSAGE_TIMEOUT).build();
        MessageManager.getInstance().putMessage(message);
        return id;
    }
}
