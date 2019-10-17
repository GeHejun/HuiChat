package com.ghj.rest.service;

import com.ghj.common.dto.response.GroupMessageResponse;
import com.ghj.common.dto.response.HistoryMessage;
import com.ghj.rest.model.GroupMessage;

/**
 * @author gehj
 * @date 2019/6/2812:55
 */
public interface GroupMessageService {
    /**
     * 新增群消息
     * @param groupMessage
     */
    void insert(GroupMessage groupMessage);

    /**
     * 分页查询群历史消息
     * @param toGroupId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    HistoryMessage<GroupMessageResponse> queryHistoryGroupMessageListForPage(Integer toGroupId, Integer pageIndex, Integer pageSize);

}
