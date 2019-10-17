package com.ghj.rest.dao;

import com.ghj.rest.model.MessageType;

/**
 * MessageTypeMapper继承基类
 */
public interface MessageTypeMapper extends MyBatisBaseDao<MessageType, Integer> {
    MessageType selectByName(String validationMessageName);
}