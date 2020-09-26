package com.ghj.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class HSession {

    /**
     * channel的唯一ID
     */
    private String channelId;


    /**
     * channel创建时间
     */
    private Long createTime;

    /**
     * channel状态
     */
    private Integer status;

    /**
     * 用户ID
     */
    private Long uId;


}
