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
@TableName("group_to_user")
public class GroupToUser extends Model<GroupToUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("to_user_id")
    private Integer toUserId;

    @TableField("group_id")
    private Integer groupId;

    /**
     * 发送时间
     */
    @TableField("send_time")
    private Date sendTime;

    /**
     * 群内用户昵称 
     */
    @TableField("group_user_nick")
    private String groupUserNick;

    public Integer getId() {
        return id;
    }

    public GroupToUser setId(Integer id) {
        this.id = id;
        return this;
    }
    public Integer getToUserId() {
        return toUserId;
    }

    public GroupToUser setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
        return this;
    }
    public Integer getGroupId() {
        return groupId;
    }

    public GroupToUser setGroupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }
    public Date getSendTime() {
        return sendTime;
    }

    public GroupToUser setSendTime(Date sendTime) {
        this.sendTime = sendTime;
        return this;
    }
    public String getGroupUserNick() {
        return groupUserNick;
    }

    public GroupToUser setGroupUserNick(String groupUserNick) {
        this.groupUserNick = groupUserNick;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "GroupToUser{" +
        "id=" + id +
        ", toUserId=" + toUserId +
        ", groupId=" + groupId +
        ", sendTime=" + sendTime +
        ", groupUserNick=" + groupUserNick +
        "}";
    }
}
