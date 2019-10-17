package com.ghj.rest.mq;

import com.alibaba.fastjson.JSON;
import com.ghj.common.base.Constant;
import com.ghj.common.dto.PersistentMessage;
import com.ghj.rest.model.GroupMessage;
import com.ghj.rest.model.GroupMessageToUser;
import com.ghj.rest.model.Message;
import com.ghj.rest.model.SystemMessage;
import com.ghj.rest.service.GroupMessageService;
import com.ghj.rest.service.GroupMessageToUserService;
import com.ghj.rest.service.MessageService;
import com.ghj.rest.service.SystemMessageService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

@Component
public class RabbitMqReceiver {

    @Resource
    MessageService messageService;

    @Resource
    GroupMessageToUserService groupMessageToUserService;

    @Resource
    GroupMessageService groupMessageService;

    @Resource
    SystemMessageService systemMessageService;




    @RabbitHandler
    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(value = Constant.EXCHANGE_A, type = ExchangeTypes.TOPIC),
                    value = @Queue(value = Constant.QUEUE_A),
                    key = Constant.ROUTING_KEY_A
            )
    )
    public void process(byte[] bytes) {
        try {
            PersistentMessage message = JSON.parseObject(new String(bytes, "UTF-8"), PersistentMessage.class);
            if (Constant.MESSAGE.equals(message.getType())) {
                if (Objects.isNull(message.getToGroupId())) {
                    Message nativeMessage = new Message();
                    nativeMessage.setId(message.getId());
                    nativeMessage.setFromUserId(message.getFromUserId());
                    nativeMessage.setMessageTypeId(nativeMessage.getMessageTypeId());
                    nativeMessage.setPostMessage(message.getPostMessage());
                    nativeMessage.setSendTime(new Date());
                    nativeMessage.setToUserId(message.getToUserId());
                    nativeMessage.setStatus(message.getStatus());
                    messageService.insertMessage(nativeMessage);
                } else {
                    GroupMessage groupMessage = new GroupMessage();
                    groupMessage.setId(message.getId());
                    groupMessage.setFromUserId(message.getFromUserId());
                    groupMessage.setContent(message.getPostMessage());
                    groupMessage.setSendTime(new Date());
                    groupMessage.setToGroupId(message.getToGroupId());
                    groupMessageService.insert(groupMessage);
                    message.getOnLineUserIds().forEach(id -> {
                        GroupMessageToUser groupMessageToUser = buildGroupMessageToUser(message, id, true);
                        groupMessageToUserService.insert(groupMessageToUser);
                    });
                    message.getOffLineUserIds().forEach(id -> {
                        GroupMessageToUser groupMessageToUser = buildGroupMessageToUser(message, id, false);
                        groupMessageToUserService.insert(groupMessageToUser);
                    });
                }
            } else {
                SystemMessage systemMessage = new SystemMessage();
                systemMessage.setContent(message.getPostMessage());
                systemMessage.setId(message.getId());
                systemMessage.setSendTime(new Date());
                systemMessage.setStatus(message.getStatus());
                systemMessage.setToUserId(message.getToUserId());
                systemMessage.setToGroupId(message.getToGroupId());
                systemMessage.setFromUserId(message.getFromUserId());
                systemMessage.setContent(message.getPostMessage());
                systemMessage.setFromFriendGroupId(message.getFromFriendGroupId());
                systemMessage.setHandleResult(Constant.PENDING_VALIDATION_MESSAGE);
                systemMessageService.insertSystemMessage(systemMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GroupMessageToUser buildGroupMessageToUser(PersistentMessage message, Integer id, boolean isReceive) {
        GroupMessageToUser groupMessageToUser = new GroupMessageToUser();
        groupMessageToUser.setGroupMessageId(message.getId());
        groupMessageToUser.setSate(isReceive);
        groupMessageToUser.setUserId(id);
        groupMessageToUser.setSendTime(new Date());
        return groupMessageToUser;
    }
}
