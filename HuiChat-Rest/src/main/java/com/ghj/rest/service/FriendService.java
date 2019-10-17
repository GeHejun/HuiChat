package com.ghj.rest.service;

import com.ghj.common.dto.request.FriendRequest;
import com.ghj.common.dto.response.FriendResponse;

import java.util.List;

/**
 * @author gehj
 * @date 2019/6/2511:36
 */
public interface FriendService {
    /**
     * 根据用户id查询好友列表
     * @param userId
     * @return
     */
    List<FriendResponse> listFriendsByUserId(Integer userId);

    Boolean agreeFriend(FriendRequest friendRequest);

    Boolean refuseFriend(Long validationMessageId);

}
