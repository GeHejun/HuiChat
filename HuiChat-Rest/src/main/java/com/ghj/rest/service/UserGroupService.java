package com.ghj.rest.service;

import com.baomidou.mybatisplus.service.IService;
import com.ghj.common.dto.UserGroupRequest;
import com.ghj.common.dto.UserGroupResponse;
import com.ghj.rest.entity.UserGroup;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GeHejun
 * @since 2019-10-22
 */
public interface UserGroupService extends IService<UserGroup> {

    List<UserGroupResponse> queryGroupList(UserGroupRequest userGroupRequest);
}
