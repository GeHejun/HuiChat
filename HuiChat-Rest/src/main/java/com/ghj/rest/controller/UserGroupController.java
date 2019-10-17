package com.ghj.rest.controller;

import com.ghj.common.base.Result;
import com.ghj.common.dto.request.GroupRequest;
import com.ghj.common.dto.response.UserGroupResponse;
import com.ghj.rest.service.UserGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author GeHejun
 */
@Controller
@RequestMapping("/userGroup")
public class UserGroupController {

    @Resource
    UserGroupService userGroupService;

    @RequestMapping("/findGroupById")
    @ResponseBody
    public Result<UserGroupResponse> findGroupById(@RequestParam("id") @NotNull Integer id) {
        return Result.defaultSuccess(userGroupService.findGroupById(id));
    }

    @RequestMapping("/findGroupByName")
    @ResponseBody
    public Result<List<UserGroupResponse>> findGroupByName(@RequestParam("name") @NotNull String name) {
        return Result.defaultSuccess(userGroupService.findGroupByName(name));
    }


    @RequestMapping("/createGroup")
    @ResponseBody
    public Result<Boolean> createGroup(@RequestBody GroupRequest groupRequest) {
        return Result.defaultSuccess(userGroupService.createGroup(groupRequest));
    }

}
