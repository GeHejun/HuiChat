package com.ghj.rest.controller;

import com.ghj.common.base.Result;
import com.ghj.common.dto.response.SessionResponse;
import com.ghj.rest.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author gehj
 * @date 2019/6/2511:08
 */

@Controller
@RequestMapping("/sessionRoute")
public class SessionController {

    @Resource
    SessionService sessionService;

    /**
     * 查询最近会话列表
     * 这里应该交给客户端去做，客户端存储最近会话
     * @param userId
     * @return
     */
    @RequestMapping("/listSessionsByUserId")
    @ResponseBody
    public Result<List<SessionResponse>> querySessionList(@NotNull Integer userId) {
        List<SessionResponse> sessionResponseList = sessionService.listSessionByUserId(userId);
        return Result.defaultSuccess(sessionResponseList);
    }
}
