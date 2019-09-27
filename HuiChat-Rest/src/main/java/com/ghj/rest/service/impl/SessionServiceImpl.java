package com.ghj.rest.service.impl;

import com.ghj.common.dto.response.SessionResponse;
import com.ghj.rest.service.SessionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gehj
 * @date 2019/6/2511:31
 */
@Service
public class SessionServiceImpl implements SessionService {
    @Override
    public List<SessionResponse> listSessionByUserId(Integer userId) {
        return null;
    }
}
