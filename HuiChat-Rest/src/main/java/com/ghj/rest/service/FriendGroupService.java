package com.ghj.rest.service;

import com.ghj.common.dto.response.FriendGroupResponse;

/**
 * @author gehj
 * @version 1.0
 * @description 获取分组
 * @date 2019/8/22 10:54
 */
public interface FriendGroupService {

    /**
     * 根据分组id获取分组
     * @param id
     * @return
     */
    FriendGroupResponse queryGroupById(Integer id);


    Integer createNewFriendGroup(Integer userId, String newFriendGroupName);
}
