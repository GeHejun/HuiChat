package com.ghj.common.dto.response;

import lombok.Data;

/**
 * @author GeHejun
 */
@Data
public class UnreadMessageResponse {

    private Integer fromUserId;

    private Integer toGroupId;

    private Integer toUserId;

    private String type;

    private Integer count;

    private String content;

    private String fromUserName;

}
