package com.ghj.rest.service.impl;

import com.ghj.common.dto.response.GroupMessageToUserResponse;
import com.ghj.common.dto.response.UnreadMessageResponse;
import com.ghj.rest.dao.GroupMessageMapper;
import com.ghj.rest.dao.GroupMessageToUserMapper;
import com.ghj.rest.dao.UserGroupMapper;
import com.ghj.rest.model.GroupMessage;
import com.ghj.rest.model.GroupMessageToUser;
import com.ghj.rest.model.UserGroup;
import com.ghj.rest.service.GroupMessageToUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author GeHejun
 */
@Service
public class GroupMessageToUserServiceImpl implements GroupMessageToUserService {

    @Resource
    GroupMessageToUserMapper groupMessageToUserMapper;

    @Resource
    GroupMessageMapper groupMessageMapper;

    @Resource
    UserGroupMapper userGroupMapper;


    @Override
    public void insert(GroupMessageToUser groupMessageToUser) {
        groupMessageToUserMapper.insert(groupMessageToUser);
    }


    @Override
    public List<GroupMessageToUserResponse> listMessageByToUserIdAndStatus(Integer toUserId, Boolean status) {
        List<GroupMessageToUser> groupMessageToUsers = groupMessageToUserMapper.selectMessageByToUserIdAndStatus(toUserId, status);
        return buildGroupMessageToUserResponseList(groupMessageToUsers);
    }

    @Override
    public Boolean readGroupMessage(Integer groupId, Integer toUserId) {
        try {
            List<GroupMessage> groupMessageList = groupMessageMapper.selectGroupMessageByToGroupId(groupId);
            groupMessageList.stream().forEach(groupMessage -> {
                List<GroupMessageToUser> groupMessageToUserList = groupMessageToUserMapper.selectMessageByGroupMessageIdAndToUserIdWithStatus(groupMessage.getId(), toUserId, false);
                groupMessageToUserList.stream().forEach(groupMessageToUser -> {
                    groupMessageToUser.setSate(true);
                    groupMessageToUserMapper.updateByPrimaryKey(groupMessageToUser);
                });
            });
        } catch (RuntimeException e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public List<UnreadMessageResponse> queryUnreadGroupMessage(Integer toUserId) {
        List<UnreadMessageResponse> unreadMessageResponseList = new ArrayList<>();
        List<GroupMessageToUser> groupMessageToUsers = groupMessageToUserMapper.selectMessageByToUserIdAndStatus(toUserId, false);
        HashMap<Integer, List<GroupMessageToUser>> groupMessageHashMap = new HashMap<>(16);
        groupMessageToUsers.stream().forEach(groupMessageToUser -> {
            List<GroupMessageToUser> groupMessageToUserList;
            GroupMessage groupMessage = groupMessageMapper.selectByPrimaryKey(groupMessageToUser.getGroupMessageId());
            if (groupMessageHashMap.containsKey(groupMessage.getToGroupId())) {
                groupMessageToUserList = groupMessageHashMap.get(groupMessage.getToGroupId());
                groupMessageToUserList.add(groupMessageToUser);
            } else {
                groupMessageToUserList = new ArrayList<>();
                groupMessageToUserList.add(groupMessageToUser);
                groupMessageHashMap.put(groupMessage.getToGroupId(), groupMessageToUserList);
            }
        });
        groupMessageHashMap.forEach((k, v)->{
            UnreadMessageResponse unreadMessageResponse = new UnreadMessageResponse();
            UserGroup userGroup = userGroupMapper.selectByPrimaryKey(k);
            unreadMessageResponse.setFromUserName(userGroup.getName());
            unreadMessageResponse.setToGroupId(k);
            unreadMessageResponse.setToUserId(toUserId);
            unreadMessageResponse.setCount(v.size());
            unreadMessageResponse.setContent(userGroup.getName()+"群有"+v.size()+"条消息未读");
            unreadMessageResponseList.add(unreadMessageResponse);
        });
        return unreadMessageResponseList;
    }


    private List<GroupMessageToUserResponse> buildGroupMessageToUserResponseList(List<GroupMessageToUser> groupMessageToUsers) {
        List<GroupMessageToUserResponse> groupMessageToUserResponseList = new ArrayList<>(groupMessageToUsers.size());
        groupMessageToUsers.forEach(groupMessageToUser -> {
            GroupMessageToUserResponse groupMessageToUserResponse = new GroupMessageToUserResponse();
            BeanUtils.copyProperties(groupMessageToUser, groupMessageToUserResponse);
            GroupMessage groupMessage = groupMessageMapper.selectByPrimaryKey(groupMessageToUser.getGroupMessageId());
            if (!Objects.isNull(groupMessage)) {
                groupMessageToUserResponse.setFromUserId(groupMessage.getFromUserId());
                groupMessageToUserResponse.setContent(groupMessage.getContent());
                groupMessageToUserResponse.setSendTime(groupMessage.getSendTime());
                groupMessageToUserResponse.setToGroupId(groupMessage.getToGroupId());
                groupMessageToUserResponseList.add(groupMessageToUserResponse);
            }
        });
        return groupMessageToUserResponseList;
    }



}
