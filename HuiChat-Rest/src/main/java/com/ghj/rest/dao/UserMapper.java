package com.ghj.rest.dao;

import com.ghj.rest.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserMapper继承基类
 * @author GeHejun
 */
public interface UserMapper extends MyBatisBaseDao<User, Integer> {

    /**
     * 通过登录名称查找
     * @param loginName
     * @return
     */
    User selectUserByLoginName(@Param("loginName") String loginName);
    /**
     * 通过昵称模糊查询
     * @param nickName
     * @return
     */
    List<User> selectUserByNickName(@Param("nickName") String nickName);
}