package com.ghj.rest.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
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
@TableName("user_state")
public class UserState extends Model<UserState> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 状态名字
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public UserState setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }

    public UserState setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "UserState{" +
        "id=" + id +
        ", name=" + name +
        "}";
    }
}
