package com.ghj.common.dto.response;

import lombok.Data;

import java.util.Date;

/**
 * @author gehj
 * @date 2019/6/2517:55
 */
@Data
public class MessageResponse {
    private Long id;

    /**
     * 接收状态
     */
    private Boolean status;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 消息类型ID
     */
    private Integer messageTypeId;

    private Integer fromUserId;

    private Integer toUserId;

    /**
     * 消息内容
     */
    private String postMessage;
}
