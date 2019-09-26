package com.ghj.common.dto.response;

import lombok.Data;

/**
 * @author gehj
 * @date 2019/6/25 10:58
 */
@Data
public class FriendResponse {


    /**
     * 朋友的ID
     */
    private Integer friendId;


    /**
     * 所属分组ID
     */
    private Integer friendGroupId;

    /**
     *
     * 备注
     */
    private String name;

}
