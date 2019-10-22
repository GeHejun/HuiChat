package com.ghj.rest.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author GeHejun
 * @since 2019-10-22
 */
public class Message extends Model<Message> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 消息内容
     */
    @TableField("post_message")
    private String postMessage;

    /**
     * 接收状态
     */
    private Boolean status;

    /**
     * 发送时间
     */
    @TableField("send_time")
    private Date sendTime;

    /**
     * 消息类型ID
     */
    @TableField("message_type_id")
    private Integer messageTypeId;

    @TableField("from_user_id")
    private Integer fromUserId;

    @TableField("to_user_id")
    private Integer toUserId;

    public Long getId() {
        return id;
    }

    public Message setId(Long id) {
        this.id = id;
        return this;
    }
    public String getPostMessage() {
        return postMessage;
    }

    public Message setPostMessage(String postMessage) {
        this.postMessage = postMessage;
        return this;
    }
    public Boolean getStatus() {
        return status;
    }

    public Message setStatus(Boolean status) {
        this.status = status;
        return this;
    }
    public Date getSendTime() {
        return sendTime;
    }

    public Message setSendTime(Date sendTime) {
        this.sendTime = sendTime;
        return this;
    }
    public Integer getMessageTypeId() {
        return messageTypeId;
    }

    public Message setMessageTypeId(Integer messageTypeId) {
        this.messageTypeId = messageTypeId;
        return this;
    }
    public Integer getFromUserId() {
        return fromUserId;
    }

    public Message setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
        return this;
    }
    public Integer getToUserId() {
        return toUserId;
    }

    public Message setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Message{" +
        "id=" + id +
        ", postMessage=" + postMessage +
        ", status=" + status +
        ", sendTime=" + sendTime +
        ", messageTypeId=" + messageTypeId +
        ", fromUserId=" + fromUserId +
        ", toUserId=" + toUserId +
        "}";
    }
}
