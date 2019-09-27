package com.ghj.rest.service;

import com.ghj.common.dto.response.GroupMessageToUserResponse;
import com.ghj.common.dto.response.UnreadMessageResponse;
import com.ghj.rest.model.GroupMessageToUser;

import java.util.List;

/**
 * @author GeHejun
 */
public interface GroupMessageToUserService {

    /**
     * 新增群->成员消息
     * @param groupMessageToUser
     */
    void insert(GroupMessageToUser groupMessageToUser);

    /**
     * 通过状态和接收人查询消息列表
     * @param toUserId
     * @param status
     * @return
     */
    List<GroupMessageToUserResponse> listMessageByToUserIdAndStatus(Integer toUserId, Boolean status);

    /**
     *  已读
     * @param groupId
     * @param toUserId
     * @return
     */
    Boolean readGroupMessage(Integer groupId, Integer toUserId);

    List<UnreadMessageResponse> queryUnreadGroupMessage(Integer toUserId);
}
