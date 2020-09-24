package com.ghj.rest.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author GeHejun
 * @since 2019-10-22
 */
@TableName("group_message")
public class GroupMessage extends Model<GroupMessage> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String content;

    @TableField("from_user_id")
    private Integer fromUserId;

    @TableField("send_time")
    private Date sendTime;

    @TableField("to_group_id")
    private Integer toGroupId;

    @TableField("message_type_id")
    private Integer messageTypeId;

    public Long getId() {
        return id;
    }

    public GroupMessage setId(Long id) {
        this.id = id;
        return this;
    }
    public String getContent() {
        return content;
    }

    public GroupMessage setContent(String content) {
        this.content = content;
        return this;
    }
    public Integer getFromUserId() {
        return fromUserId;
    }

    public GroupMessage setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
        return this;
    }
    public Date getSendTime() {
        return sendTime;
    }

    public GroupMessage setSendTime(Date sendTime) {
        this.sendTime = sendTime;
        return this;
    }
    public Integer getToGroupId() {
        return toGroupId;
    }

    public GroupMessage setToGroupId(Integer toGroupId) {
        this.toGroupId = toGroupId;
        return this;
    }
    public Integer getMessageTypeId() {
        return messageTypeId;
    }

    public GroupMessage setMessageTypeId(Integer messageTypeId) {
        this.messageTypeId = messageTypeId;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "GroupMessage{" +
        "id=" + id +
        ", content=" + content +
        ", fromUserId=" + fromUserId +
        ", sendTime=" + sendTime +
        ", toGroupId=" + toGroupId +
        ", messageTypeId=" + messageTypeId +
        "}";
    }
}
