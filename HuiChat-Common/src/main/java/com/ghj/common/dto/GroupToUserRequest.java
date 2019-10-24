package com.ghj.common.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/24 13:41
 */
@Builder
@Data
public class GroupToUserRequest {
    private Integer toUserId;
}
