package com.ghj.common.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserGroupResponse {

    private Integer id;

    /**
     * 群名称
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     *  群主ID
     */
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
}
