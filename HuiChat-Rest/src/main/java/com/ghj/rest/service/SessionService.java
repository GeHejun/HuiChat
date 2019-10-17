package com.ghj.rest.service;

import com.ghj.common.dto.response.SessionResponse;

import java.util.List;

/**
 * @author gehj
 * @date 2019/6/2511:31
 */
public interface SessionService {
    /**
     * 通过用户查找会话
     * @param userId
     * @return
     */
    List<SessionResponse> listSessionByUserId(Integer userId);
}
