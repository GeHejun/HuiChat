package com.ghj.web.service;

import com.ghj.common.base.Result;
import com.ghj.common.dto.FriendRequest;
import com.ghj.common.dto.FriendResponse;
import com.ghj.common.dto.UserRequest;
import com.ghj.common.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/24 12:01
 */
@FeignClient
public interface RestService {

    @RequestMapping("/queryUserList")
    @ResponseBody
    Result<List<UserResponse>> queryUserList(UserRequest userRequest);

    @RequestMapping("/queryUser")
    @ResponseBody
    Result<UserResponse> queryUser(UserRequest userRequest);


    @RequestMapping("/queryFriendList")
    @ResponseBody
    Result<List<FriendResponse>> queryFriendList(FriendRequest friendRequest);
}
