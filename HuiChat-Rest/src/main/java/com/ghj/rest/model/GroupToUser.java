package com.ghj.rest.model;

import java.io.Serializable;
import java.util.Date;

/**
 * group_to_user
 * @author 
 */
public class GroupToUser implements Serializable {

    private Integer id;

    private Integer toUserId;

    private Integer groupId;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 群内用户昵称 
     */
    private String groupUserNick;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getGroupUserNick() {
        return groupUserNick;
    }

    public void setGroupUserNick(String groupUserNick) {
        this.groupUserNick = groupUserNick;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        GroupToUser other = (GroupToUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getToUserId() == null ? other.getToUserId() == null : this.getToUserId().equals(other.getToUserId()))
            && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
            && (this.getSendTime() == null ? other.getSendTime() == null : this.getSendTime().equals(other.getSendTime()))
            && (this.getGroupUserNick() == null ? other.getGroupUserNick() == null : this.getGroupUserNick().equals(other.getGroupUserNick()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getToUserId() == null) ? 0 : getToUserId().hashCode());
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getSendTime() == null) ? 0 : getSendTime().hashCode());
        result = prime * result + ((getGroupUserNick() == null) ? 0 : getGroupUserNick().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", toUserId=").append(toUserId);
        sb.append(", groupId=").append(groupId);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", groupUserNick=").append(groupUserNick);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}