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
@TableName("friend_group")
public class FriendGroup extends Model<FriendGroup> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分组名字
     */
    private String name;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public FriendGroup setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }

    public FriendGroup setName(String name) {
        this.name = name;
        return this;
    }
    public Integer getUserId() {
        return userId;
    }

    public FriendGroup setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FriendGroup{" +
        "id=" + id +
        ", name=" + name +
        ", userId=" + userId +
        "}";
    }
}
