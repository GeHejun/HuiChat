package com.ghj.rest.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
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
@TableName("user_group")
public class UserGroup extends Model<UserGroup> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 群名称
     */
    private String name;

    /**
     * 创建时间 
     */
    @TableField("create_time")
    private Date createTime;

    /**
     *  群主ID
     */
    @TableField("admin_id")
    private Integer adminId;

    /**
     *   群图标
     */
    private String icon;

    /**
     * 群公告 
     */
    private String notice;

    /**
     * 群简介  
     */
    private String intro;

    public Integer getId() {
        return id;
    }

    public UserGroup setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }

    public UserGroup setName(String name) {
        this.name = name;
        return this;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public UserGroup setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
    public Integer getAdminId() {
        return adminId;
    }

    public UserGroup setAdminId(Integer adminId) {
        this.adminId = adminId;
        return this;
    }
    public String getIcon() {
        return icon;
    }

    public UserGroup setIcon(String icon) {
        this.icon = icon;
        return this;
    }
    public String getNotice() {
        return notice;
    }

    public UserGroup setNotice(String notice) {
        this.notice = notice;
        return this;
    }
    public String getIntro() {
        return intro;
    }

    public UserGroup setIntro(String intro) {
        this.intro = intro;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
        "id=" + id +
        ", name=" + name +
        ", createTime=" + createTime +
        ", adminId=" + adminId +
        ", icon=" + icon +
        ", notice=" + notice +
        ", intro=" + intro +
        "}";
    }
}
