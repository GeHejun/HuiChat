package com.ghj.rest.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ghj.common.dto.GroupToUserRequest;
import com.ghj.common.dto.GroupToUserResponse;
import com.ghj.rest.entity.GroupToUser;
import com.ghj.rest.mapper.GroupToUserDao;
import com.ghj.rest.service.GroupToUserService;
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
public class GroupToUserServiceImpl extends ServiceImpl<GroupToUserDao, GroupToUser> implements GroupToUserService {

    @Override
    public List<GroupToUserResponse> queryGroupToUserList(GroupToUserRequest groupToUserRequest) {
        GroupToUser groupToUser = new GroupToUser();
        BeanUtils.copyProperties(groupToUserRequest, groupToUser);
        EntityWrapper<GroupToUser> groupToUserEntityWrapper = new EntityWrapper<>(groupToUser);
        List<GroupToUser> groupToUserList = this.selectList(groupToUserEntityWrapper);
        List<GroupToUserResponse> groupToUserResponseList = new ArrayList<>(groupToUserList.size());
        groupToUserList.forEach(gtu -> {
            GroupToUserResponse groupToUserResponse = new GroupToUserResponse();
            BeanUtils.copyProperties(gtu, groupToUserResponse);
            groupToUserResponseList.add(groupToUserResponse);
        });
        return groupToUserResponseList;
    }
}
