package com.ghj.rest.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
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
public class Friend extends Model<Friend> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 朋友的ID
     */
    @TableField("friend_id")
    private Integer friendId;

    /**
     *  自己的ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 备注昵称 
     */
    private String name;

    /**
     * 好友类型
     */
    @TableField("friend_type_id")
    private Integer friendTypeId;

    /**
     * 所属分组ID
     */
    @TableField("friend_group_id")
    private Integer friendGroupId;

    public Integer getId() {
        return id;
    }

    public Friend setId(Integer id) {
        this.id = id;
        return this;
    }
    public Integer getFriendId() {
        return friendId;
    }

    public Friend setFriendId(Integer friendId) {
        this.friendId = friendId;
        return this;
    }
    public Integer getUserId() {
        return userId;
    }

    public Friend setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
    public String getName() {
        return name;
    }

    public Friend setName(String name) {
        this.name = name;
        return this;
    }
    public Integer getFriendTypeId() {
        return friendTypeId;
    }

    public Friend setFriendTypeId(Integer friendTypeId) {
        this.friendTypeId = friendTypeId;
        return this;
    }
    public Integer getFriendGroupId() {
        return friendGroupId;
    }

    public Friend setFriendGroupId(Integer friendGroupId) {
        this.friendGroupId = friendGroupId;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Friend{" +
        "id=" + id +
        ", friendId=" + friendId +
        ", userId=" + userId +
        ", name=" + name +
        ", friendTypeId=" + friendTypeId +
        ", friendGroupId=" + friendGroupId +
        "}";
    }
}
