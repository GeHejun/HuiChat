package com.ghj.rest.service;

import com.ghj.common.dto.request.UserRequest;
import com.ghj.common.dto.response.UserResponse;

import java.util.List;

/**
 * @author GeHejun
 * @date 2019-06-24
 */
public interface UserService {
    /**
     * 验证用户
     * @param loginName
     * @param password
     * @return
     */
    UserResponse validateUser(String loginName, String password);

    /**
     * 通过id查找用户信息
     * @param id
     * @return
     */
    UserResponse getUserById(Integer id);

    /**
     * 检查用户名是否可用
     * @param loginName
     * @return
     */
    Boolean checkUser(String loginName);

    /**
     * 用户注册接口
     * @param userRequest
     * @return
     */
    UserResponse register(UserRequest userRequest);

    /**
     * 通过用户账号添加用户
     * @param nickName
     * @return
     */
    List<UserResponse> queryUserByNickName(String nickName);

    /**
     * 查询用户状态
     * @param userId
     * @return
     */
    Boolean queryUserState(Integer userId);
}
