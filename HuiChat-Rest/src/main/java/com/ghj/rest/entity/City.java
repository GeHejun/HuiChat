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
public class City extends Model<City> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String code;

    @TableField("province_id")
    private Integer provinceId;

    public Integer getId() {
        return id;
    }

    public City setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }

    public City setName(String name) {
        this.name = name;
        return this;
    }
    public String getCode() {
        return code;
    }

    public City setCode(String code) {
        this.code = code;
        return this;
    }
    public Integer getProvinceId() {
        return provinceId;
    }

    public City setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "City{" +
        "id=" + id +
        ", name=" + name +
        ", code=" + code +
        ", provinceId=" + provinceId +
        "}";
    }
}
