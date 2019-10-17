package com.ghj.common.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class GroupToUserResponse {

    private Integer toUserId;

    private Integer groupId;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 群内用户昵称
     */
    private String groupUserNick;
}
