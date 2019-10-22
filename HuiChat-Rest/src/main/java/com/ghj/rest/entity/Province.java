package com.ghj.rest.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
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
public class Province extends Model<Province> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String code;

    @TableField("nation_id")
    private Integer nationId;

    public Integer getId() {
        return id;
    }

    public Province setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }

    public Province setName(String name) {
        this.name = name;
        return this;
    }
    public String getCode() {
        return code;
    }

    public Province setCode(String code) {
        this.code = code;
        return this;
    }
    public Integer getNationId() {
        return nationId;
    }

    public Province setNationId(Integer nationId) {
        this.nationId = nationId;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Province{" +
        "id=" + id +
        ", name=" + name +
        ", code=" + code +
        ", nationId=" + nationId +
        "}";
    }
}
