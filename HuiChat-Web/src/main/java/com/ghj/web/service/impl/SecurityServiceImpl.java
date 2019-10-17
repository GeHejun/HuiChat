package com.ghj.web.service.impl;

import com.ghj.common.base.Constant;
import com.ghj.common.base.Result;
import com.ghj.common.dto.request.FriendRequest;
import com.ghj.common.dto.request.GroupRequest;
import com.ghj.common.dto.request.UserRequest;
import com.ghj.common.dto.response.UserGroupResponse;
import com.ghj.common.dto.response.UserResponse;
import com.ghj.common.exception.UserException;
import com.ghj.common.util.DesEncryptDecrypt;
import com.ghj.web.service.RestService;
import com.ghj.web.service.SecurityService;
import com.ghj.web.vo.GroupVO;
import com.ghj.web.vo.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Resource
    RestService restService;


    @Override
    public UserVO validateUser(String loginName, String password) {
        Result<UserResponse> result = restService.login(loginName, password);
        if (!(result.isSuccess() && Constant.LOGIN_SUCCESS_CODE == result.getCode())) {
            throw new UserException();
        }
        UserResponse userResponse = result.getData();
        UserVO userVO = UserVO.builder()
                .id(userResponse.getId().toString())
                .username(userResponse.getName())
                .token(userResponse.getToken())
                .build();
        return userVO;
    }

    @Override
    public Boolean checkUser(String loginName) {
        return restService.checkUser(loginName).getData();
    }

    @Override
    public UserResponse register(UserRequest userRequest) {
        userRequest.setPassWord(DesEncryptDecrypt.getInstance().encrypt(userRequest.getPassWord()));
        userRequest.setUserStateId(restService.queryStateByName(Constant.OFF_LINE).getData().getId());
        return restService.register(userRequest).getData();
    }

    @Override
    public List<UserVO> findUser(String nickName) {
        List<UserResponse> userResponseList = restService.queryUserByNickName(nickName).getData();
        if (CollectionUtils.isEmpty(userResponseList)) {
            throw new RuntimeException();
        }
        List<UserVO> userVOList = new ArrayList<>(userResponseList.size());
        userResponseList.stream().forEach(userResponse -> {
            UserVO userVO = UserVO.builder()
                    .id(userResponse.getId().toString())
                    .username(userResponse.getName())
                    .nickName(userResponse.getNickName())
                    .avatar(userResponse.getHeadPortrait())
                    .token(userResponse.getToken())
                    .build();
            userVOList.add(userVO);
        });
        return userVOList;
    }

    @Override
    public List<GroupVO> findGroup(String name) {
        List<UserGroupResponse> userGroupResponseList = restService.findGroupByName(name).getData();
        if (CollectionUtils.isEmpty(userGroupResponseList)) {
            throw new RuntimeException();
        }
        List<GroupVO> groupVOList = new ArrayList<>(userGroupResponseList.size());
        userGroupResponseList.stream().forEach(userGroupResponse -> {
            GroupVO groupVO = GroupVO.builder().avatar(userGroupResponse.getIcon())
                    .groupname(userGroupResponse.getName())
                    .adminId(userGroupResponse.getAdminId().toString())
                    .id(userGroupResponse.getId().toString()).build();
            groupVOList.add(groupVO);
        });

        return groupVOList;
    }

    @Override
    public Boolean agreeFriend(FriendRequest friendRequest) {
        return restService.agreeFriend(friendRequest).getData();
    }

    @Override
    public Boolean refuseFriend(Long validationMessageId) {
        return restService.refuseFriend(validationMessageId).getData();
    }

    @Override
    public Integer agreeGroup(Long validationMessageId, Integer fromUserId, Integer toGroupId) {
        return restService.agreeGroup(validationMessageId, fromUserId, toGroupId).getData();
    }

    @Override
    public Boolean createGroup(GroupRequest groupRequest) {
        return restService.createGroup(groupRequest).getData();
    }

    @Override
    public Integer createNewFriendGroup(Integer userId, String newFriendGroupName) {
        return restService.createNewFriendGroup(userId, newFriendGroupName).getData();
    }
}
