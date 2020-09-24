package com.ghj.common.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/24 13:06
 */
@Data
@Builder
public class FriendRequest implements Serializable {

    private Integer userId;

}
