package com.ghj.keep.context;

import io.netty.channel.ChannelHandlerContext;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class HSession {

    /**
     * channel
     */
    private ChannelHandlerContext ctx;

    /**
     * channel创建时间
     */
    private Long createTime;

    /**
     * 用户ID
     */
    private Long uId;

    /**
     * ip地址
     */
    private String clientAddress;

    /**
     * 登陆地址
     */
    private String location;


}
