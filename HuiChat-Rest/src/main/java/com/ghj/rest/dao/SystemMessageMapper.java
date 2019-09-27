package com.ghj.rest.dao;

import com.ghj.rest.model.SystemMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SystemMessageMapper继承基类
 * @author GeHejun
 */
@Repository
public interface SystemMessageMapper extends MyBatisBaseDao<SystemMessage, Long> {

    Integer selectUnreadValidationMessageNum(@Param("toUserId") Integer toUserId, @Param("status") Boolean status);

    List<SystemMessage> selectValidationMessage(@Param("toUserId") Integer toUserId);

    List<SystemMessage> selectValidationMessageByToUserIdWithStatus(@Param("toUserId") Integer toUserId, @Param("status") Boolean status);
}