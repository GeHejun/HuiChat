package com.ghj.rest.web;


import com.ghj.common.base.Result;
import com.ghj.common.dto.FriendRequest;
import com.ghj.common.dto.FriendResponse;
import com.ghj.rest.service.FriendService;
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
@RequestMapping("/friend")
public class FriendController {

    @Resource
    FriendService friendService;

    @RequestMapping("/queryFriendList")
    @ResponseBody
    public Result<List<FriendResponse>> queryFriendList(FriendRequest friendRequest) {
        List<FriendResponse> friendResponseList = friendService.queryFriendList(friendRequest);
        return Result.defaultSuccess(friendResponseList);
    }
}
