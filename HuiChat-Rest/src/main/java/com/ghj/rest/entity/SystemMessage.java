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
@TableName("system_message")
public class SystemMessage extends Model<SystemMessage> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 来源-如果为空代表是系统消息
     */
    @TableField("from_user_id")
    private Integer fromUserId;

    /**
     * 发送目的
     */
    @TableField("to_user_id")
    private Integer toUserId;

    @TableField("send_time")
    private Date sendTime;

    private Integer status;

    private String content;

    @TableField("to_group_id")
    private Integer toGroupId;

    @TableField("from_friend_group_id")
    private Integer fromFriendGroupId;

    private String remark;

    @TableField("handle_result")
    private Integer handleResult;

    public Long getId() {
        return id;
    }

    public SystemMessage setId(Long id) {
        this.id = id;
        return this;
    }
    public Integer getFromUserId() {
        return fromUserId;
    }

    public SystemMessage setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
        return this;
    }
    public Integer getToUserId() {
        return toUserId;
    }

    public SystemMessage setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
        return this;
    }
    public Date getSendTime() {
        return sendTime;
    }

    public SystemMessage setSendTime(Date sendTime) {
        this.sendTime = sendTime;
        return this;
    }
    public Integer getStatus() {
        return status;
    }

    public SystemMessage setStatus(Integer status) {
        this.status = status;
        return this;
    }
    public String getContent() {
        return content;
    }

    public SystemMessage setContent(String content) {
        this.content = content;
        return this;
    }
    public Integer getToGroupId() {
        return toGroupId;
    }

    public SystemMessage setToGroupId(Integer toGroupId) {
        this.toGroupId = toGroupId;
        return this;
    }
    public Integer getFromFriendGroupId() {
        return fromFriendGroupId;
    }

    public SystemMessage setFromFriendGroupId(Integer fromFriendGroupId) {
        this.fromFriendGroupId = fromFriendGroupId;
        return this;
    }
    public String getRemark() {
        return remark;
    }

    public SystemMessage setRemark(String remark) {
        this.remark = remark;
        return this;
    }
    public Integer getHandleResult() {
        return handleResult;
    }

    public SystemMessage setHandleResult(Integer handleResult) {
        this.handleResult = handleResult;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SystemMessage{" +
        "id=" + id +
        ", fromUserId=" + fromUserId +
        ", toUserId=" + toUserId +
        ", sendTime=" + sendTime +
        ", status=" + status +
        ", content=" + content +
        ", toGroupId=" + toGroupId +
        ", fromFriendGroupId=" + fromFriendGroupId +
        ", remark=" + remark +
        ", handleResult=" + handleResult +
        "}";
    }
}
