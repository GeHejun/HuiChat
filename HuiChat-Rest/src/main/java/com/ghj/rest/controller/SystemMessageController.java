package com.ghj.rest.controller;

import com.ghj.common.base.Result;
import com.ghj.common.dto.response.SystemMessageResponse;
import com.ghj.rest.service.SystemMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping("/systemMessage")
public class SystemMessageController {

    @Resource
    SystemMessageService systemMessageService;

    /**
     *
     * @param toUserId
     * @return
     */
    @RequestMapping("/queryValidationMessage")
    @ResponseBody
    public Result<List<SystemMessageResponse>> queryValidationMessage(@NotNull @RequestParam("toUserId")Integer toUserId) {
        return Result.defaultSuccess(systemMessageService.queryValidationMessage(toUserId));
    }

    /**
     *
     * @param toUserId
     * @return
     */
    @RequestMapping("/readValidationMessage")
    @ResponseBody
    public Result<Boolean> readValidationMessage(@NotNull @RequestParam("toUserId")Integer toUserId) {
        return Result.defaultSuccess(systemMessageService.readValidationMessage(toUserId));
    }

    /**
     *
     * @param toUserId
     * @return
     */
    @RequestMapping("/queryUnreadValidationMessageNum")
    @ResponseBody
    public Result<Integer> queryUnreadValidationMessageNum(@NotNull @RequestParam("toUserId")Integer toUserId) {
        return Result.defaultSuccess(systemMessageService.queryUnreadValidationMessageNum(toUserId));
    }
}
