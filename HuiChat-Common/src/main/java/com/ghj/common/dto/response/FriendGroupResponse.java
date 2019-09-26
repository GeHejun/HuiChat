package com.ghj.common.dto.response;

import lombok.Data;

/**
 * @author gehj
 * @date 2019/7/19:34
 */
@Data
public class FriendGroupResponse {

    private Integer id;


    /**
     * 分组名字
     */
    private String name;

    /**
     * 用户ID
     */
    private Integer userId;
}
