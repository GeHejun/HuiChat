package com.ghj.rest.dao;

import com.ghj.rest.model.UserGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserGroupMapper继承基类
 * @author GeHejun
 */
public interface UserGroupMapper extends MyBatisBaseDao<UserGroup, Integer> {
    /**
     * 通过群名称查询群组
     * @param name
     * @return
     */
    List<UserGroup> selectGroupByName(@Param("name") String name);

    /**
     * 通过群主查询群
     * @param toUserId
     * @return
     */
    List<UserGroup> selectGroupByAdminId(@Param("toUserId") Integer toUserId);


    int insertAndGetId(UserGroup userGroup);

}