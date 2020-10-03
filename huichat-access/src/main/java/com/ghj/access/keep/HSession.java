package com.ghj.access.keep;

import io.netty.channel.ChannelHandlerContext;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.kafka.common.protocol.types.Field;


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
