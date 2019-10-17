package com.ghj.rest.service.impl;

import com.ghj.common.dto.response.UserStateResponse;
import com.ghj.rest.dao.UserStateMapper;
import com.ghj.rest.model.UserState;
import com.ghj.rest.service.UserStateService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserStateServiceImpl implements UserStateService {
    @Resource
    UserStateMapper userStateMapper;

    @Override
    public UserStateResponse queryUserStateById(Integer id) {
        UserState userState = userStateMapper.selectByPrimaryKey(id);
        UserStateResponse userStateResponse = new UserStateResponse();
        BeanUtils.copyProperties(userState, userStateResponse);
        return userStateResponse;
    }

    @Override
    public UserStateResponse queryStateByName(String name) {
        UserState userState = userStateMapper.selectStateByName(name);
        UserStateResponse userStateResponse = new UserStateResponse();
        BeanUtils.copyProperties(userState, userStateResponse);
        return userStateResponse;
    }
}
