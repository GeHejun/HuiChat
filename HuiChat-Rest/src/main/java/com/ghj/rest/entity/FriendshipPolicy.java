package com.ghj.rest.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author GeHejun
 * @since 2019-10-22
 */
@TableName("friendship_policy")
public class FriendshipPolicy extends Model<FriendshipPolicy> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *  好友添加方式    
     */
    @TableField("friendship_policy")
    private String friendshipPolicy;

    public Integer getId() {
        return id;
    }

    public FriendshipPolicy setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getFriendshipPolicy() {
        return friendshipPolicy;
    }

    public FriendshipPolicy setFriendshipPolicy(String friendshipPolicy) {
        this.friendshipPolicy = friendshipPolicy;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FriendshipPolicy{" +
        "id=" + id +
        ", friendshipPolicy=" + friendshipPolicy +
        "}";
    }
}
