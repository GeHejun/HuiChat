package com.ghj.rest.dao;

import com.ghj.rest.model.GroupMessageToUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * GroupMessageToUserMapper继承基类
 */
public interface GroupMessageToUserMapper extends MyBatisBaseDao<GroupMessageToUser, Integer> {

    List<GroupMessageToUser> selectMessageByToUserIdAndStatus(@Param("toUserId") Integer toUserId, @Param("status") Boolean status);

    List<GroupMessageToUser> selectMessageByGroupMessageIdAndToUserIdWithStatus(@Param("groupMessageId") Long groupMessageId, @Param("toUserId") Integer toUserId, @Param("status") Boolean status);

}