package com.ghj.rest.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ghj.common.dto.UserRequest;
import com.ghj.common.dto.UserResponse;
import com.ghj.rest.entity.User;
import com.ghj.rest.mapper.UserDao;
import com.ghj.rest.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public UserResponse queryUser(UserRequest userRequest) {
        User user = new User();
        BeanUtils.copyProperties(userRequest, user);
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>(user);
        user = this.selectOne(userEntityWrapper);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        return userResponse;
    }

    /**
     * 查询用户
     * @param userRequest
     * @return
     */
    @Override
    public List<UserResponse> queryUserList(UserRequest userRequest) {
        User user = new User();
        BeanUtils.copyProperties(userRequest, user);
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>(user);
        List<User> userList = this.selectList(userEntityWrapper);
        List<UserResponse> userResponseList = new ArrayList<>(userList.size());
        userList.forEach(u -> {
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(u, userResponse);
            userResponseList.add(userResponse);
        });
        return userResponseList;
    }
}
