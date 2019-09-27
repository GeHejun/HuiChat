package com.ghj.rest.controller;

import com.ghj.common.base.Result;
import com.ghj.common.dto.response.FriendGroupResponse;
import com.ghj.rest.service.FriendGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;

/**
 *
 * @author GeHejun
 * @date 209-07-01
 */
@Controller
@RequestMapping("/friendGroup")
public class FriendGroupController {

    @Autowired
    FriendGroupService friendGroupService;

    @RequestMapping("/queryGroupById")
    @ResponseBody
    public Result<FriendGroupResponse> queryGroupById(@NotNull @RequestParam("id") Integer id) {
        FriendGroupResponse friendGroupResponse = friendGroupService.queryGroupById(id);
        return Result.defaultSuccess(friendGroupResponse);
    }

    @RequestMapping("/createNewFriendGroup")
    @ResponseBody
    public Result<Integer> createNewFriendGroup(@RequestParam("userId") Integer userId,  @RequestParam("newFriendGroupName") String newFriendGroupName) {
        return Result.defaultSuccess(friendGroupService.createNewFriendGroup(userId , newFriendGroupName));
    }
}
