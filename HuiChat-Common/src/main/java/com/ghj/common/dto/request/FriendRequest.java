package com.ghj.common.dto.request;

import lombok.Data;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/9/11 10:54
 */
@Data
public class FriendRequest {

    Long validationMessageId;

    Integer fromUserId;

    Integer fromFriendGroupId;

    Integer toUserId;

    Integer toFriendGroupId;

    String newFriendGroupName;
}
