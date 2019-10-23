package com.ghj.rest.service;

import com.baomidou.mybatisplus.service.IService;
import com.ghj.common.dto.UserResponse;
import com.ghj.rest.entity.User;

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
     * 通过id查询用户
     * @param id
     * @return
     */
    UserResponse queryUserById(Integer id);
}
