package com.ghj.rest.service.impl;

import com.ghj.common.dto.response.FriendGroupResponse;
import com.ghj.rest.dao.FriendGroupMapper;
import com.ghj.rest.model.FriendGroup;
import com.ghj.rest.service.FriendGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/8/22 10:54
 */
@Service
public class FriendGroupServiceImpl implements FriendGroupService {
    @Resource
    FriendGroupMapper friendGroupMapper;

    @Override
    public FriendGroupResponse queryGroupById(Integer id) {
        FriendGroup friendGroup = friendGroupMapper.selectByPrimaryKey(id);
        FriendGroupResponse friendGroupResponse = new FriendGroupResponse();
        BeanUtils.copyProperties(friendGroup, friendGroupResponse);
        return friendGroupResponse;
    }

    @Override
    public Integer createNewFriendGroup(Integer userId, String newFriendGroupName) {
        FriendGroup friendGroup = new FriendGroup();
        friendGroup.setName(newFriendGroupName);
        friendGroup.setUserId(userId);
        friendGroupMapper.insertAndGetId(friendGroup);
        return friendGroup.getId();
    }
}
