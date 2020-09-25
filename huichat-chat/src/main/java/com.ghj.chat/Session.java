package com.ghj.chat;

import io.netty.channel.Channel;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author GeHejun
 * @date 2019-06-24
 */
@Builder
@Data
public class Session {

    Channel channel;

    /**
     * 主键、自增
     */
    private Integer userId;

    private Date buildTime;

}
