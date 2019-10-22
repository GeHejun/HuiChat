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
@TableName("group_message_to_user")
public class GroupMessageToUser extends Model<GroupMessageToUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("user_id")
    private Integer userId;

    @TableField("group_message_id")
    private Long groupMessageId;

    private Boolean status;

    @TableField("send_time")
    private Date sendTime;

    public Integer getId() {
        return id;
    }

    public GroupMessageToUser setId(Integer id) {
        this.id = id;
        return this;
    }
    public Integer getUserId() {
        return userId;
    }

    public GroupMessageToUser setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
    public Long getGroupMessageId() {
        return groupMessageId;
    }

    public GroupMessageToUser setGroupMessageId(Long groupMessageId) {
        this.groupMessageId = groupMessageId;
        return this;
    }
    public Boolean getStatus() {
        return status;
    }

    public GroupMessageToUser setStatus(Boolean status) {
        this.status = status;
        return this;
    }
    public Date getSendTime() {
        return sendTime;
    }

    public GroupMessageToUser setSendTime(Date sendTime) {
        this.sendTime = sendTime;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "GroupMessageToUser{" +
        "id=" + id +
        ", userId=" + userId +
        ", groupMessageId=" + groupMessageId +
        ", status=" + status +
        ", sendTime=" + sendTime +
        "}";
    }
}
