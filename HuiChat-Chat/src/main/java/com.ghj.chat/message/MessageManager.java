package com.ghj.chat.message;

import com.ghj.common.exception.MessageException;
import com.ghj.common.util.ThreadPoolManager;
import com.ghj.protocol.Message;
import com.ghj.protocol.MessageProto;

import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author GeHejun
 * @date 2019/6/24 13:13
 */
public class MessageManager {

    private MessageManager() {}

    private static Object MESSAGE_MANAGER_LOCK = new Object();

    private static volatile MessageManager messageManager;

    public static MessageManager getInstance() {
        if (messageManager == null) {
            synchronized (MESSAGE_MANAGER_LOCK) {
                if (messageManager == null) {
                    messageManager = new MessageManager();
                    messageManager.takeMessage();
                }
            }
        }
        return messageManager;
    }

    private ConcurrentLinkedQueue waitSendMessageQueue = new ConcurrentLinkedQueue();


    public void putMessage(Message message) {
        if (Objects.isNull(message)) {
            throw new MessageException();
        }

        waitSendMessageQueue.add(message);
    }

    public void takeMessage() {
        for (;;) {
            if (waitSendMessageQueue.isEmpty()) {
                continue;
            }
            ThreadPoolManager.getsInstance().execute(new MessageSender((Message) waitSendMessageQueue.poll()));
        }
    }





}
