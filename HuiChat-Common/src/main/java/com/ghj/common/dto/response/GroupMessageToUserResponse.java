package com.ghj.common.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class GroupMessageToUserResponse {

    private Integer userId;

    private Long groupMessageId;

    private Boolean status;

    private Date sendTime;

    private Integer fromUserId;

    private String content;

    private Integer toGroupId;
}
