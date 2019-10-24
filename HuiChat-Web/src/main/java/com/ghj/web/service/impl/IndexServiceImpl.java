package com.ghj.web.service.impl;

import com.ghj.common.base.Result;
import com.ghj.common.dto.*;
import com.ghj.web.service.IndexService;
import com.ghj.web.service.RestService;
import com.ghj.web.vo.MainFrameVO;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/24 13:27
 */
public class IndexServiceImpl implements IndexService {

    @Resource
    RestService restService;

    @Override
    public MainFrameVO queryMainFrame(Integer id) {
        UserRequest userRequest = UserRequest.builder().id(id).build();
        UserResponse userResponse = restService.queryUser(userRequest).getData();

        FriendRequest friendRequest = FriendRequest.builder().userId(id).build();
        List<FriendResponse> friendResponseList = restService.queryFriendList(friendRequest).getData();

        GroupToUserRequest groupToUserRequest = GroupToUserRequest.builder().toUserId(id).build();
        List<GroupToUserResponse> groupToUserResponseList = restService.queryGroupToUserList(groupToUserRequest).getData();

        groupToUserResponseList.forEach(groupToUserResponse -> {
            UserGroupRequest userGroupRequest = UserGroupRequest.builder().groupId(groupToUserResponse.getGroupId()).build();
            List<UserGroupResponse> userGroupResponseList = restService.queryGroupList(userGroupRequest).getData();
        });
        return null;
    }
}
