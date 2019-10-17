package com.ghj.rest.service.impl;

import com.ghj.common.base.Constant;
import com.ghj.common.dto.request.FriendRequest;
import com.ghj.common.dto.response.FriendResponse;
import com.ghj.rest.dao.FriendGroupMapper;
import com.ghj.rest.dao.FriendMapper;
import com.ghj.rest.dao.SystemMessageMapper;
import com.ghj.rest.dao.UserMapper;
import com.ghj.rest.model.Friend;
import com.ghj.rest.model.FriendGroup;
import com.ghj.rest.model.SystemMessage;
import com.ghj.rest.model.User;
import com.ghj.rest.service.FriendService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gehj
 * @date 2019/6/2511:36
 */
@Service
public class FriendServiceImpl implements FriendService {

    @Resource
    FriendMapper friendMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    SystemMessageMapper systemMessageMapper;

    @Resource
    FriendGroupMapper friendGroupMapper;

    @Override
    public List<FriendResponse> listFriendsByUserId(Integer userId) {
        List<Friend> friendList = friendMapper.listFriendListByUserId(userId);
        List<FriendResponse> friendResponseList = new ArrayList<>(friendList.size());
        friendList.stream().forEach(friend -> {
            FriendResponse friendResponse = new FriendResponse();
            BeanUtils.copyProperties(friend, friendResponse);
            friendResponseList.add(friendResponse);
        });
        return friendResponseList;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Boolean agreeFriend(FriendRequest friendRequest) {
        try {
            if (friendRequest.getNewFriendGroupName() != null) {
                FriendGroup friendGroup = new FriendGroup();
                friendGroup.setName(friendRequest.getNewFriendGroupName());
                friendGroup.setUserId(friendRequest.getToUserId());
                friendGroupMapper.insertAndGetId(friendGroup);
                friendRequest.setToFriendGroupId(friendGroup.getId());
            }
            SystemMessage systemMessage = systemMessageMapper.selectByPrimaryKey(friendRequest.getValidationMessageId());
            systemMessage.setHandleResult(Constant.AGREE_VALIDATION_MESSAGE);
            systemMessageMapper.updateByPrimaryKey(systemMessage);
            User toUser = userMapper.selectByPrimaryKey(friendRequest.getToUserId());
            Friend toFriend = new Friend();
            toFriend.setUserId(friendRequest.getFromUserId());
            toFriend.setFriendId(friendRequest.getToUserId());
            toFriend.setName(toUser.getName());
            toFriend.setFriendGroupId(friendRequest.getToFriendGroupId());
            friendMapper.insert(toFriend);
            User fromUser = userMapper.selectByPrimaryKey(friendRequest.getFromUserId());
            Friend fromFriend = new Friend();
            fromFriend.setUserId(friendRequest.getToUserId());
            fromFriend.setFriendId(friendRequest.getFromUserId());
            fromFriend.setFriendGroupId(friendRequest.getToFriendGroupId());
            fromFriend.setName(fromUser.getName());
            friendMapper.insert(fromFriend);
        } catch (RuntimeException e) {
            return  false;
        }
        return true;
    }

    @Override
    public Boolean refuseFriend(Long validationMessageId) {
        try {
            SystemMessage systemMessage = systemMessageMapper.selectByPrimaryKey(validationMessageId);
            systemMessage.setHandleResult(Constant.REFUSE_VALIDATION_MESSAGE);
            systemMessageMapper.updateByPrimaryKey(systemMessage);
        } catch (RuntimeException e) {
            return  false;
        }
        return true;

    }


}
