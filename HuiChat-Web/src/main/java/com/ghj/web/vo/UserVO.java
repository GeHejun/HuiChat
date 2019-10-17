package com.ghj.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/8/22 10:12
 */
@Data
@AllArgsConstructor
@Builder
public class UserVO {

    private String username;

    private String id;

    private String status;

    private String sign;

    private String avatar;

    private String token;

    private String nickName;

}
