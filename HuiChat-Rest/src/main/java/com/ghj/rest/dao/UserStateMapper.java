package com.ghj.rest.dao;

import com.ghj.rest.model.UserState;

/**
 * UserStateMapper继承基类
 * @author GeHejun
 */
public interface UserStateMapper extends MyBatisBaseDao<UserState, Integer> {
    /**
     * 通过状态名称查询状态
     * @param name
     * @return
     */
    UserState selectStateByName(String name);
}