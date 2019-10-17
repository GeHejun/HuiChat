package com.ghj.rest.service;

import com.ghj.common.dto.response.GroupToUserResponse;

import java.util.List;

/**
 *
 * @author GeHejun
 */
public interface GroupToUserService {
    /**
     * 通过接收人查找对应消息
     * @param userId
     * @return
     */
    List<GroupToUserResponse> findGroupByUserId(Integer userId);

    /**
     * 通过群查找群内成员
     * @param groupId
     * @return
     */
    List<Integer> findUserIdByGroupId(Integer groupId);

    Integer agreeGroup(Long validationMessageId, Integer fromUserId, Integer toGroupId);


}
