package com.ghj.rest.controller;


import com.ghj.common.base.Result;
import com.ghj.common.dto.response.GroupToUserResponse;
import com.ghj.rest.service.GroupToUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author gehj
 * @date 2019/7/19:40
 */
@RequestMapping("/groupToUser")
@Controller
public class GroupToUserController {

    @Resource
    GroupToUserService groupToUserService;

    @RequestMapping("/findGroupByUserId")
    @ResponseBody
    public Result<List<GroupToUserResponse>> findGroupByUserId(@RequestParam("userId") @NotNull Integer userId) {
        return Result.defaultSuccess(groupToUserService.findGroupByUserId(userId));
    }

    @RequestMapping("/findUserIdByGroupId")
    @ResponseBody
    public Result<List<Integer>> findUserIdByGroupId(@RequestParam("groupId") @NotNull Integer groupId) {
        return Result.defaultSuccess(groupToUserService.findUserIdByGroupId(groupId));
    }

    @RequestMapping("/agreeGroup")
    @ResponseBody
    public Result<Integer> agreeGroup(@RequestParam("validationMessageId") Long validationMessageId,@RequestParam("fromUserId") Integer fromUserId,@RequestParam("toGroupId") Integer toGroupId) {
        return Result.defaultSuccess(groupToUserService.agreeGroup(validationMessageId, fromUserId, toGroupId));
    }

}
