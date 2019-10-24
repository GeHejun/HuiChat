package com.ghj.rest.service;

import com.baomidou.mybatisplus.service.IService;
import com.ghj.common.dto.GroupToUserRequest;
import com.ghj.common.dto.GroupToUserResponse;
import com.ghj.rest.entity.GroupToUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GeHejun
 * @since 2019-10-22
 */
public interface GroupToUserService extends IService<GroupToUser> {

    List<GroupToUserResponse> queryGroupToUserList(GroupToUserRequest groupToUserRequest);
}
