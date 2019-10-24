package com.ghj.common.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/23 17:46
 */
@Data
@Builder
public class UserRequest {

    @NotEmpty(message = "id不能为空")
    private Integer id;
}
