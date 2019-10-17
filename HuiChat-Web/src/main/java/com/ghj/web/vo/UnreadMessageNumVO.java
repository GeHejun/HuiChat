package com.ghj.web.vo;

import lombok.Data;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/9/9 13:58
 */
@Data
public class UnreadMessageNumVO {

    private Integer fromUserId;

    private Integer fromUserName;

    private Integer toGroupId;

    private Integer toUserId;

    private String type;

    private Integer count;

    private String content;
}
