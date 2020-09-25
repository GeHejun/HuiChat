package com.ghj.rest.service;

import com.baomidou.mybatisplus.service.IService;
import com.ghj.common.dto.UserRequest;
import com.ghj.common.dto.UserResponse;
import com.ghj.rest.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GeHejun
 * @since 2019-10-22
 */
public interface UserService extends IService<User> {
    /**
     * 查询用户
     * @param userRequest
     * @return
     */
    UserResponse queryUser(UserRequest userRequest);

    /**
     * 查询用户
     * @param userRequest
     * @return
     */
    List<UserResponse> queryUserList(UserRequest userRequest);
}
