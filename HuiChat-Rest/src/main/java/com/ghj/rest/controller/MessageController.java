package com.ghj.rest.controller;

import com.ghj.common.base.Result;
import com.ghj.common.dto.response.HistoryMessage;
import com.ghj.common.dto.response.MessageResponse;
import com.ghj.common.dto.response.UnreadMessageResponse;
import com.ghj.rest.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author gehj
 * @date 2019/6/2517:43
 */
@RequestMapping("/message")
@Controller
public class MessageController {

    @Resource
    MessageService messageService;

    @RequestMapping("/queryHistoryMessageListForPage")
    @ResponseBody
    public Result<HistoryMessage<MessageResponse>> queryHistoryMessageListForPage(
            @NotNull @RequestParam("fromUserId") Integer fromUserId,
            @NotNull @RequestParam("toUserId") Integer toUserId,
                                                          @RequestParam(defaultValue = "1") Integer pageIndex,
                                                          @RequestParam(defaultValue = "10") Integer pageSize) {
        HistoryMessage<MessageResponse> historyMessage = messageService.queryHistoryMessageListForPage(fromUserId, toUserId, pageIndex, pageSize);
        return Result.defaultSuccess(historyMessage);
    }

    @RequestMapping("/queryMessage/{id}")
    @ResponseBody
    public Result<MessageResponse> queryMessagePage(@PathVariable Integer id) {
        MessageResponse messageResponse = messageService.queryMessageById(id);
        return Result.defaultSuccess(messageResponse);
    }


    @RequestMapping("/queryMessageWithToUserIdAndStatus")
    @ResponseBody
    public Result<List<MessageResponse>> queryMessageWithToUserIdAndStatus(@NotNull @RequestParam("toUserId") Integer toUserId, @RequestParam(defaultValue = "false") @NotNull Boolean status) {
        List<MessageResponse> messageResponseList = messageService.queryMessageByToUserIdWithStatus(toUserId, status);
        return Result.defaultSuccess(messageResponseList);
    }

    /**
     * 消息标记为已读 好友消息
     * @param fromUserId
     * @param toUserId
     * @return
     */
    @RequestMapping("/readFriendMessage")
    @ResponseBody
    public Result<Boolean> readFriendMessage(@NotNull @RequestParam("fromUserId") Integer fromUserId, @NotNull @RequestParam("toUserId") Integer toUserId) {
        return Result.defaultSuccess(messageService.readFriendMessage(fromUserId, toUserId));
    }


    /**
     * 查询好友未读消息条数
     * @param toUserId
     * @return
     */
    @RequestMapping("/queryUnreadFriendMessage")
    @ResponseBody
    public Result<List<UnreadMessageResponse>> queryUnreadFriendMessage(@NotNull @RequestParam("toUserId") Integer toUserId) {
        return Result.defaultSuccess(messageService.queryUnreadFriendMessage(toUserId));
    }


}
