package com.ghj.rest.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author GeHejun
 * @since 2019-10-22
 */
@TableName("group_message_user_to_user")
public class GroupMessageUserToUser extends Model<GroupMessageUserToUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("from_user_id")
    private Integer fromUserId;

    @TableField("from_user_name")
    private String fromUserName;

    @TableField("to_user_id")
    private Integer toUserId;

    private String content;

    private Boolean status;

    @TableField("send_time")
    private Date sendTime;

    @TableField("user_group_id")
    private Integer userGroupId;

    public Integer getId() {
        return id;
    }

    public GroupMessageUserToUser setId(Integer id) {
        this.id = id;
        return this;
    }
    public Integer getFromUserId() {
        return fromUserId;
    }

    public GroupMessageUserToUser setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
        return this;
    }
    public String getFromUserName() {
        return fromUserName;
    }

    public GroupMessageUserToUser setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
        return this;
    }
    public Integer getToUserId() {
        return toUserId;
    }

    public GroupMessageUserToUser setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
        return this;
    }
    public String getContent() {
        return content;
    }

    public GroupMessageUserToUser setContent(String content) {
        this.content = content;
        return this;
    }
    public Boolean getStatus() {
        return status;
    }

    public GroupMessageUserToUser setStatus(Boolean status) {
        this.status = status;
        return this;
    }
    public Date getSendTime() {
        return sendTime;
    }

    public GroupMessageUserToUser setSendTime(Date sendTime) {
        this.sendTime = sendTime;
        return this;
    }
    public Integer getUserGroupId() {
        return userGroupId;
    }

    public GroupMessageUserToUser setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "GroupMessageUserToUser{" +
        "id=" + id +
        ", fromUserId=" + fromUserId +
        ", fromUserName=" + fromUserName +
        ", toUserId=" + toUserId +
        ", content=" + content +
        ", status=" + status +
        ", sendTime=" + sendTime +
        ", userGroupId=" + userGroupId +
        "}";
    }
}
