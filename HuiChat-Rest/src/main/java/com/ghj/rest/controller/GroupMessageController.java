package com.ghj.rest.controller;

import com.ghj.common.base.Result;
import com.ghj.common.dto.response.GroupMessageResponse;
import com.ghj.common.dto.response.HistoryMessage;
import com.ghj.rest.service.GroupMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @author GeHejun
 */
@Controller
@RequestMapping("/groupMessage")
public class GroupMessageController {
    @Resource
    GroupMessageService groupMessageService;

    @RequestMapping("/queryHistoryGroupMessageListForPage")
    @ResponseBody
    public Result<HistoryMessage<GroupMessageResponse>> queryHistoryGroupMessageListForPage(@NotNull @RequestParam("toUserId") Integer toGroupId,
                                                                                            @RequestParam(defaultValue = "1") Integer pageIndex,
                                                                                            @RequestParam(defaultValue = "10") Integer pageSize) {
        HistoryMessage<GroupMessageResponse> historyMessage = groupMessageService.queryHistoryGroupMessageListForPage(toGroupId, pageIndex, pageSize);
        return Result.defaultSuccess(historyMessage);
    }



}
