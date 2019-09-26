package com.ghj.common.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class SystemMessageResponse {

    private Long id;

    /**
     * 来源
     */
    private Integer fromUserId;

    /**
     * 发送目的
     */
    private Integer toUserId;

    private Date sendTime;

    private Boolean status;

    private String content;

    private Integer toGroupId;

    private Integer fromFriendGroupId;

    private Integer handleResult;

}
