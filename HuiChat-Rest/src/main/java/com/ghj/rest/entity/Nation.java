package com.ghj.rest.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author GeHejun
 * @since 2019-10-22
 */
public class Nation extends Model<Nation> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名字
     */
    private String name;

    private String code;

    public Integer getId() {
        return id;
    }

    public Nation setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }

    public Nation setName(String name) {
        this.name = name;
        return this;
    }
    public String getCode() {
        return code;
    }

    public Nation setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Nation{" +
        "id=" + id +
        ", name=" + name +
        ", code=" + code +
        "}";
    }
}
