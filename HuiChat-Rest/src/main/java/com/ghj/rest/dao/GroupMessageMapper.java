package com.ghj.rest.dao;

import com.ghj.rest.model.GroupMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * GoupMessageMapper继承基类
 */
public interface GroupMessageMapper extends MyBatisBaseDao<GroupMessage, Long> {

    List<GroupMessage> selectGroupMessageByToGroupId(@Param("toGroupId") Integer toGroupId);

}