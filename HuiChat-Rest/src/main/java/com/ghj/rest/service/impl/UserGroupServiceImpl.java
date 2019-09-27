package com.ghj.rest.service.impl;

import com.ghj.common.dto.request.GroupRequest;
import com.ghj.common.dto.response.UserGroupResponse;
import com.ghj.rest.dao.GroupToUserMapper;
import com.ghj.rest.dao.UserGroupMapper;
import com.ghj.rest.dao.UserMapper;
import com.ghj.rest.model.GroupToUser;
import com.ghj.rest.model.User;
import com.ghj.rest.model.UserGroup;
import com.ghj.rest.service.UserGroupService;
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
public class UserGroupServiceImpl implements UserGroupService {

    @Resource
    UserGroupMapper userGroupMapper;

    @Resource
    GroupToUserMapper groupToUserMapper;

    @Resource
    UserMapper userMapper;

    @Override
    public UserGroupResponse findGroupById(Integer id) {
        UserGroup userGroup = userGroupMapper.selectByPrimaryKey(id);
        UserGroupResponse userGroupResponse = new UserGroupResponse();
        BeanUtils.copyProperties(userGroup, userGroupResponse);
        return userGroupResponse;
    }

    @Override
    public List<UserGroupResponse> findGroupByName(String name) {
        List<UserGroup> userGroupList = userGroupMapper.selectGroupByName(name);
        List<UserGroupResponse> userGroupResponseList = new ArrayList<>(userGroupList.size());
        userGroupList.stream().forEach(userGroup -> {
            UserGroupResponse userGroupResponse = new UserGroupResponse();
            BeanUtils.copyProperties(userGroup, userGroupResponse);
            userGroupResponseList.add(userGroupResponse);
        });
        return userGroupResponseList;
    }

    @Override
    public Boolean createGroup(GroupRequest groupRequest) {
        try {
            UserGroup userGroup = new UserGroup();
            userGroup.setAdminId(groupRequest.getAdminId());
            userGroup.setCreateTime(new Date());
            userGroup.setName(groupRequest.getName());
            userGroupMapper.insertAndGetId(userGroup);
            Integer groupId = userGroup.getId();
            groupRequest.getFriendIds().forEach(id -> {
                User user = userMapper.selectByPrimaryKey(id);
                GroupToUser groupToUser = new GroupToUser();
                groupToUser.setGroupId(groupId);
                groupToUser.setSendTime(new Date());
                groupToUser.setToUserId(id);
                groupToUser.setGroupUserNick(user.getNickName());
                groupToUserMapper.insert(groupToUser);
            });
            User user = userMapper.selectByPrimaryKey(groupRequest.getAdminId());
            GroupToUser groupToUser = new GroupToUser();
            groupToUser.setGroupId(groupId);
            groupToUser.setSendTime(new Date());
            groupToUser.setToUserId(groupRequest.getAdminId());
            groupToUser.setGroupUserNick(user.getNickName());
            groupToUserMapper.insert(groupToUser);
        } catch (Exception e) {

        }
        return true;
    }


}
