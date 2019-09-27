package com.ghj.rest.dao;

import com.ghj.rest.model.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * MessageMapper继承基类
 */
public interface MessageMapper extends MyBatisBaseDao<Message, Integer> {

    List<Message> selectMessageByToUserId(@Param("fromUserId") Integer fromUserId, @Param("toUserId") Integer toUserId);

    List<Message> selectMessageByToUserIdWithStatus(@Param("toUserId") Integer toUserId, @Param("status") Boolean status);

    List<Message> selectMessageByFromUserIdAndToUserIdWithStatus(@Param("fromUserId") Integer fromUserId, @Param("toUserId") Integer toUserId, @Param("status") Boolean status);

}