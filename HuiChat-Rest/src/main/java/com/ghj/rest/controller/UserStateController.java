package com.ghj.rest.controller;

import com.ghj.common.base.Result;
import com.ghj.common.dto.response.UserStateResponse;
import com.ghj.rest.service.UserStateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/8/22 11:35
 */
@RestController
@RequestMapping("/state")
public class UserStateController {

    @Resource
    UserStateService userStateService;

    @RequestMapping("/queryUserStateById")
    public Result<UserStateResponse> queryUserStateById(@NotNull @RequestParam(name = "id") Integer id) {
        return Result.defaultSuccess(userStateService.queryUserStateById(id));
    }

    /**
     * 查询状态通过名称
     * @param name
     * @return
     */
    @RequestMapping("/queryStateByName")
    @ResponseBody
    public Result<UserStateResponse> queryStateByName(@RequestParam("name") String name) {
        return Result.defaultSuccess(userStateService.queryStateByName(name));
    }
}
