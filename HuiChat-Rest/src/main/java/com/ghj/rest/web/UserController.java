package com.ghj.rest.web;


import com.ghj.common.base.Result;
import com.ghj.common.dto.UserRequest;
import com.ghj.common.dto.UserResponse;
import com.ghj.rest.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author GeHejun
 * @since 2019-10-22
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/queryUser")
    @ResponseBody
    public Result<UserResponse> queryUser(UserRequest userRequest) {
        UserResponse userResponse = userService.queryUser(userRequest);
        return Result.defaultSuccess(userResponse);
    }

    @RequestMapping("/queryUserList")
    @ResponseBody
    public Result<List<UserResponse>> queryUserList(UserRequest userRequest) {
        List<UserResponse> userResponseList = userService.queryUserList(userRequest);
        return Result.defaultSuccess(userResponseList);
    }
}
