package com.ghj.web.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/9/4 13:54
 */
@Data
@Builder
public class MessageBoxVO {

    private String id;

    private String content;

    private Integer uid;

    private Integer from;

    private Integer from_group;

    private Integer type;

    private String remark;

    private String href;

    private Integer read;

    private String time;

    private UserVO user;

    private Integer toGroupId;

    private String toGroupName;
}
