package com.ghj.rest.service;

import com.baomidou.mybatisplus.service.IService;
import com.ghj.common.dto.FriendRequest;
import com.ghj.common.dto.FriendResponse;
import com.ghj.rest.entity.Friend;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GeHejun
 * @since 2019-10-22
 */
public interface FriendService extends IService<Friend> {

    List<FriendResponse> queryFriendList(FriendRequest friendRequest);
}
