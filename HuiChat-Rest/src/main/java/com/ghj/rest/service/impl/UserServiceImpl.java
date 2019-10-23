package com.ghj.rest.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ghj.common.dto.UserResponse;
import com.ghj.rest.entity.User;
import com.ghj.rest.mapper.UserDao;
import com.ghj.rest.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GeHejun
 * @since 2019-10-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Override
    public UserResponse queryUserById(Integer id) {
        User user = this.selectById(id);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        return userResponse;
    }
}
