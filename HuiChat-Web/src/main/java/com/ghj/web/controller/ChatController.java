package com.ghj.web.controller;

import com.ghj.androidsdk.MessageSender;
import com.ghj.common.base.ContentType;
import com.ghj.common.base.Result;
import com.ghj.web.vo.MessageVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/15 14:31
 */
@Controller
@RestController("/chat")
public class ChatController {

    @RequestMapping("/sendMsg")
    public Result<Long> sendTextMessage(@RequestParam MessageVO messageVO) {
        return Result.defaultSuccess(MessageSender.sendMessage(messageVO.getContent(), ContentType.Text.getCode(), messageVO.getIsGroup(), messageVO.getFrom(), messageVO.getTo()));
    }
}