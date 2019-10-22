package com.ghj.rest.entity;

import com.baomidou.mybatisplus.activerecord.Model;
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
@TableName("friend_type")
public class FriendType extends Model<FriendType> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类型名称
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public FriendType setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }

    public FriendType setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FriendType{" +
        "id=" + id +
        ", name=" + name +
        "}";
    }
}
