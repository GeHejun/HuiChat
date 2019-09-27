package com.ghj.rest.service.impl;

import com.ghj.common.dto.response.SystemMessageResponse;
import com.ghj.rest.dao.SystemMessageMapper;
import com.ghj.rest.model.SystemMessage;
import com.ghj.rest.service.SystemMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/9/6 14:46
 */
@Service
public class SystemMessageImpl implements SystemMessageService {

    @Resource
    SystemMessageMapper systemMessageMapper;


    @Override
    public void insertSystemMessage(SystemMessage systemMessage) {
        systemMessageMapper.insert(systemMessage);
    }

    @Override
    public Integer queryUnreadValidationMessageNum(Integer toUserId) {
        return systemMessageMapper.selectUnreadValidationMessageNum(toUserId, Boolean.FALSE);
    }

    @Override
    public List<SystemMessageResponse> queryValidationMessage(Integer toUserId) {
        List<SystemMessage> systemMessageList = systemMessageMapper.selectValidationMessage(toUserId);
        List<SystemMessageResponse> systemMessageResponseList = new ArrayList<>(systemMessageList.size());
        systemMessageList.stream().forEach(systemMessage -> {
            SystemMessageResponse systemMessageResponse = SystemMessageResponse.builder()
                    .fromUserId(systemMessage.getFromUserId())
                    .id(systemMessage.getId())
                    .status(systemMessage.getStatus())
                    .sendTime(systemMessage.getSendTime())
                    .toGroupId(systemMessage.getToGroupId())
                    .toUserId(systemMessage.getToUserId())
                    .content(systemMessage.getContent())
                    .fromFriendGroupId(systemMessage.getFromFriendGroupId())
                    .handleResult(systemMessage.getHandleResult())
                    .build();
            systemMessageResponseList.add(systemMessageResponse);
        });
        return systemMessageResponseList;
    }

    @Override
    public Boolean readValidationMessage(Integer toUserId) {
        try {
            List<SystemMessage> systemMessageList = systemMessageMapper.selectValidationMessageByToUserIdWithStatus(toUserId, false);
            systemMessageList.forEach(systemMessage -> {
                systemMessage.setStatus(Boolean.TRUE);
                systemMessageMapper.updateByPrimaryKey(systemMessage);
            });
        } catch (RuntimeException e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
