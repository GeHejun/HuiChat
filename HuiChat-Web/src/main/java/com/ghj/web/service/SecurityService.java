package com.ghj.web.service;


import com.ghj.common.dto.request.FriendRequest;
import com.ghj.common.dto.request.GroupRequest;
import com.ghj.common.dto.request.UserRequest;
import com.ghj.common.dto.response.UserResponse;
import com.ghj.web.vo.GroupVO;
import com.ghj.web.vo.UserVO;

import java.util.List;

/**
 * @author GeHejun
 */
public interface SecurityService {
    /**
     * 校验用户信息
     * @param loginName
     * @param password
     * @return
     */
    UserVO validateUser(String loginName, String password);

    /**
     * 检查用户名是否可用
     * @param loginName
     * @return
     */
    Boolean checkUser(String loginName);

    /**
     * 注册新用户
     * @param userRequest
     * @return
     */
//    UserResponse register(UserRequest userRequest);

    /**
     * 通过用户名查找用户
     * @param nickName
     * @return
     */
    List<UserVO> findUser(String nickName);

    /**
     * 通过群名称查询群
     * @param name
     * @return
     */
    List<GroupVO> findGroup(String name);

    Boolean agreeFriend(FriendRequest friendRequest);

    Boolean refuseFriend(Long validationMessageId);

    Integer agreeGroup(Long validationMessageId, Integer fromUserId, Integer toGroupId);

    Boolean createGroup(GroupRequest groupRequest);

    Integer createNewFriendGroup(Integer userId, String newFriendGroupName);
}
