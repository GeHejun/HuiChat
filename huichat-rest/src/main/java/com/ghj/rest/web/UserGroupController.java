package com.ghj.rest.web;


import com.ghj.common.base.Result;
import com.ghj.common.dto.UserGroupRequest;
import com.ghj.common.dto.UserGroupResponse;
import com.ghj.rest.service.UserGroupService;
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
@RequestMapping("/userGroup")
public class UserGroupController {

    @Resource
    UserGroupService userGroupService;

    @RequestMapping("/queryGroupList")
    @ResponseBody
    public Result<List<UserGroupResponse>> queryGroupList(UserGroupRequest userGroupRequest) {
        List<UserGroupResponse> userGroupResponseList = userGroupService.queryGroupList(userGroupRequest);
        return Result.defaultSuccess(userGroupResponseList);
    }
}
