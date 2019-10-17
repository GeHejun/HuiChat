package com.ghj.rest.service.impl;

import com.ghj.common.base.Constant;
import com.ghj.common.dto.response.GroupToUserResponse;
import com.ghj.rest.dao.GroupToUserMapper;
import com.ghj.rest.dao.SystemMessageMapper;
import com.ghj.rest.dao.UserMapper;
import com.ghj.rest.model.GroupToUser;
import com.ghj.rest.model.SystemMessage;
import com.ghj.rest.model.User;
import com.ghj.rest.service.GroupToUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author GeHejun
 */
@Service
public class GroupToUserServiceImpl implements GroupToUserService {

    @Resource
    GroupToUserMapper groupToUserMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    SystemMessageMapper systemMessageMapper;




    @Override
    public List<GroupToUserResponse> findGroupByUserId(Integer userId) {
        List<GroupToUser> groupToUserList = groupToUserMapper.selectGroupToUserByUserId(userId);
        List<GroupToUserResponse> groupToUserResponseList = new ArrayList<>(groupToUserList.size());
        groupToUserList.stream().forEach(groupToUser -> {
            GroupToUserResponse groupToUserResponse = new GroupToUserResponse();
            BeanUtils.copyProperties(groupToUser, groupToUserResponse);
            groupToUserResponseList.add(groupToUserResponse);
        });
        return groupToUserResponseList;
    }

    @Override
    public List<Integer> findUserIdByGroupId(Integer groupId) {
        List<GroupToUser> groupToUserList = groupToUserMapper.selectGroupToUserByGroupId(groupId);
        List<Integer> userIds = new ArrayList<>(groupToUserList.size());
        groupToUserList.stream().forEach(groupToUser -> userIds.add(groupToUser.getToUserId()));
        return userIds;
    }

    @Override
    public Integer agreeGroup(Long validationMessageId, Integer fromUserId, Integer toGroupId) {
        try {
            SystemMessage systemMessage = systemMessageMapper.selectByPrimaryKey(validationMessageId);
            systemMessage.setHandleResult(Constant.AGREE_VALIDATION_MESSAGE);
            systemMessageMapper.updateByPrimaryKey(systemMessage);
            User user = userMapper.selectByPrimaryKey(fromUserId);
            GroupToUser groupToUser = new GroupToUser();
            groupToUser.setGroupId(toGroupId);
            groupToUser.setToUserId(fromUserId);
            groupToUser.setSendTime(new Date());
            groupToUser.setGroupUserNick(user.getNickName());
            groupToUserMapper.insert(groupToUser);
         } catch (Exception e) {

        }
        return toGroupId;
    }

}
