package com.ghj.rest.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ghj.common.dto.FriendRequest;
import com.ghj.common.dto.FriendResponse;
import com.ghj.rest.entity.Friend;
import com.ghj.rest.mapper.FriendDao;
import com.ghj.rest.service.FriendService;
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
public class FriendServiceImpl extends ServiceImpl<FriendDao, Friend> implements FriendService {

    @Override
    public List<FriendResponse> queryFriendList(FriendRequest friendRequest) {
        Friend friend = new Friend();
        BeanUtils.copyProperties(friendRequest , friend);
        EntityWrapper<Friend> friendEntityWrapper = new EntityWrapper<>(friend);
        List<Friend> friendList = this.selectList(friendEntityWrapper);
        List<FriendResponse> friendResponseList = new ArrayList<>(friendList.size());
        friendResponseList.forEach(f -> {
            FriendResponse friendResponse  = new FriendResponse();
            BeanUtils.copyProperties(f, friendResponse);
            friendResponseList.add(friendResponse);
        });
        return friendResponseList;
    }
}
