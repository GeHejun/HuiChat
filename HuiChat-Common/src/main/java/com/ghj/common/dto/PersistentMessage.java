package com.ghj.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/8/29 18:59
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersistentMessage {

    private Long id;

    /**
     * 接收状态
     */
    private Boolean status;

    /**
     * 发送时间
     */
    private String sendTime;

    /**
     * 消息类型ID
     */
    private Integer messageTypeId;

    /**
     *
     */
    private Integer fromUserId;

    /**
     * 消息内容
     */
    private String postMessage;

    private Integer toGroupId;

    private Integer toUserId;

    private List<Integer> onLineUserIds;

    private List<Integer> offLineUserIds;

    /**
     * 消息类型 群验证消息-好友验证消息-普通消息
     */
    private String type;

    private Integer fromFriendGroupId;

}
