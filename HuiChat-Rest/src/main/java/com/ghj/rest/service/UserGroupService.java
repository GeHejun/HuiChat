package com.ghj.rest.service;

import com.ghj.common.dto.request.GroupRequest;
import com.ghj.common.dto.response.UserGroupResponse;

import java.util.List;

/**
 * @author GeHejun
 */
public interface UserGroupService {
    /**
     * 通过id查找群
     * @param id
     * @return
     */
    UserGroupResponse findGroupById(Integer id);

    /**
     * 通过群名称查询群
     * @param name
     * @return
     */
    List<UserGroupResponse> findGroupByName(String name);


    Boolean createGroup(GroupRequest groupRequest);
}
