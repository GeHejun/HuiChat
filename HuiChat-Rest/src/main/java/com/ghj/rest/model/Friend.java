package com.ghj.rest.model;

import java.io.Serializable;

/**
 * friend
 * @author 
 */
public class Friend implements Serializable {
    private Integer id;

    /**
     * 朋友的ID
     */
    private Integer friendId;

    /**
     *  自己的ID
     */
    private Integer userId;

    /**
     * 备注昵称 
     */
    private String name;

    /**
     * 好友类型
     */
    private Integer friendTypeId;

    /**
     * 所属分组ID
     */
    private Integer friendGroupId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFriendTypeId() {
        return friendTypeId;
    }

    public void setFriendTypeId(Integer friendTypeId) {
        this.friendTypeId = friendTypeId;
    }

    public Integer getFriendGroupId() {
        return friendGroupId;
    }

    public void setFriendGroupId(Integer friendGroupId) {
        this.friendGroupId = friendGroupId;
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
        Friend other = (Friend) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFriendId() == null ? other.getFriendId() == null : this.getFriendId().equals(other.getFriendId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getFriendTypeId() == null ? other.getFriendTypeId() == null : this.getFriendTypeId().equals(other.getFriendTypeId()))
            && (this.getFriendGroupId() == null ? other.getFriendGroupId() == null : this.getFriendGroupId().equals(other.getFriendGroupId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFriendId() == null) ? 0 : getFriendId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getFriendTypeId() == null) ? 0 : getFriendTypeId().hashCode());
        result = prime * result + ((getFriendGroupId() == null) ? 0 : getFriendGroupId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", friendId=").append(friendId);
        sb.append(", userId=").append(userId);
        sb.append(", name=").append(name);
        sb.append(", friendTypeId=").append(friendTypeId);
        sb.append(", friendGroupId=").append(friendGroupId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}