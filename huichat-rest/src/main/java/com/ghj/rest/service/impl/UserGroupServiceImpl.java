package com.ghj.rest.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ghj.common.dto.UserGroupRequest;
import com.ghj.common.dto.UserGroupResponse;
import com.ghj.rest.entity.UserGroup;
import com.ghj.rest.mapper.UserGroupDao;
import com.ghj.rest.service.UserGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GeHejun
 * @since 2019-10-22
 */
@Service
public class UserGroupServiceImpl extends ServiceImpl<UserGroupDao, UserGroup> implements UserGroupService {

    @Override
    public List<UserGroupResponse> queryGroupList(UserGroupRequest userGroupRequest) {
        UserGroup userGroup = new UserGroup();
        userGroup.setId(userGroupRequest.getGroupId());
        EntityWrapper<UserGroup> userGroupEntityWrapper = new EntityWrapper<>(userGroup);
        List<UserGroup> userGroupList = this.selectList(userGroupEntityWrapper);
        List<UserGroupResponse> userGroupResponseList = new ArrayList<>(userGroupList.size());
        userGroupList.forEach(ug -> {
            UserGroupResponse userGroupResponse = new UserGroupResponse();
            BeanUtils.copyProperties(ug, userGroupResponse);
            userGroupResponseList.add(userGroupResponse);
        });
        return userGroupResponseList;
    }
}
