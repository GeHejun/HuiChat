package com.ghj.rest.web;


import com.ghj.common.base.Result;
import com.ghj.common.dto.GroupToUserRequest;
import com.ghj.common.dto.GroupToUserResponse;
import com.ghj.rest.service.GroupToUserService;
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
@RequestMapping("/groupToUser")
public class GroupToUserController {

    @Resource
    GroupToUserService groupToUserService;

    @RequestMapping("/queryGroupToUserList")
    @ResponseBody
    public Result<List<GroupToUserResponse>> queryGroupToUserList(GroupToUserRequest groupToUserRequest) {
        return Result.defaultSuccess(groupToUserService.queryGroupToUserList(groupToUserRequest));
    }
}
