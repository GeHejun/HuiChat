package com.ghj.rest.controller;

import com.ghj.common.base.Result;
import com.ghj.common.dto.request.FriendRequest;
import com.ghj.common.dto.response.FriendResponse;
import com.ghj.rest.service.FriendService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author GeHejun
 * @date 2019-06-24
 */
@Controller
@RequestMapping("/friend")
public class FriendController {

    @Resource
    FriendService friendService;


    @RequestMapping("/queryFriendList")
    @ResponseBody
    public Result<List<FriendResponse>> queryFriendList(@Valid @RequestParam("userId") Integer userId) {
        List<FriendResponse> friendResponseList = friendService.listFriendsByUserId(userId);
        return Result.defaultSuccess(friendResponseList);
    }

    @RequestMapping("/agreeFriend")
    @ResponseBody
    public Result<Boolean> agreeFriend(@RequestBody FriendRequest friendRequest) {
        return Result.defaultSuccess(friendService.agreeFriend(friendRequest));
    }

    @RequestMapping("/refuseFriend")
    @ResponseBody
    public Result<Boolean> refuseFriend(@NotNull @RequestParam("validationMessageId") Long validationMessageId) {
        return Result.defaultSuccess(friendService.refuseFriend(validationMessageId));
    }



}
