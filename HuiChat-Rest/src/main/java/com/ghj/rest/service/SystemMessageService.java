package com.ghj.rest.service;

import com.ghj.common.dto.response.SystemMessageResponse;
import com.ghj.rest.model.SystemMessage;

import java.util.List;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/9/6 14:44
 */
public interface SystemMessageService {
    /**
     * 插入系统消息
     * @param systemMessage
     */
    void insertSystemMessage(SystemMessage systemMessage);

    Integer queryUnreadValidationMessageNum(Integer toUserId);

    List<SystemMessageResponse> queryValidationMessage(Integer toUserId);

    Boolean readValidationMessage(Integer toUserId);
}
