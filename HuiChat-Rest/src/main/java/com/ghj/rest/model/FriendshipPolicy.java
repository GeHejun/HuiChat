package com.ghj.rest.model;

import java.io.Serializable;

/**
 * friendship_policy
 * @author 
 */
public class FriendshipPolicy implements Serializable {
    private Integer id;

    /**
     *  好友添加方式    
     */
    private String friendshipPolicy;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFriendshipPolicy() {
        return friendshipPolicy;
    }

    public void setFriendshipPolicy(String friendshipPolicy) {
        this.friendshipPolicy = friendshipPolicy;
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
        FriendshipPolicy other = (FriendshipPolicy) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFriendshipPolicy() == null ? other.getFriendshipPolicy() == null : this.getFriendshipPolicy().equals(other.getFriendshipPolicy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFriendshipPolicy() == null) ? 0 : getFriendshipPolicy().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", friendshipPolicy=").append(friendshipPolicy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}