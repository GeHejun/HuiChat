package com.ghj.rest.dao;

import com.ghj.rest.model.GroupToUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * GroupToUserMapper继承基类
 */
public interface GroupToUserMapper extends MyBatisBaseDao<GroupToUser, Integer> {

    List<GroupToUser> selectGroupToUserByUserId(@Param("userId") Integer userId);

    List<GroupToUser> selectGroupToUserByGroupId(Integer groupId);


}