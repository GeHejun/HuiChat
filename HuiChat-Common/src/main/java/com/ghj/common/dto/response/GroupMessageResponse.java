package com.ghj.common.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/9/3 13:11
 */
@Data
@Builder
public class GroupMessageResponse {

    private Long id;

    private Integer fromUserId;

    private Date sendTime;

    private String content;

    private Integer toGroupId;

    private Integer messageTypeId;
}
