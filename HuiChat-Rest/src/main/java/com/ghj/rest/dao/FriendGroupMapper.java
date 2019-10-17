package com.ghj.rest.dao;

import com.ghj.rest.model.FriendGroup;

/**
 * FriendGroupMapper继承基类
 */
public interface FriendGroupMapper extends MyBatisBaseDao<FriendGroup, Integer> {
    int insertAndGetId(FriendGroup friendGroup);
}