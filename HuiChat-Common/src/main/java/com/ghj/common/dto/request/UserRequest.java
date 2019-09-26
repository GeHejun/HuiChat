package com.ghj.common.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class UserRequest {
    /**
     * 主键、自增
     */
    private Integer id;

    /**
     * 登陆账号
     */
    private String loginName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 简介
     */
    private String intro;

    /**
     * 头像
     */
    private String headPortrait;

    /**
     * 生肖
     */
    private String zodiac;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 星座
     */
    private String constellation;

    /**
     * 血型
     */
    private String bloodType;

    /**
     * 毕业学校
     */
    private String schoolTag;

    /**
     * 职业
     */
    private String vocation;

    /**
     * 国家ID
     */
    private Integer nationId;

    /**
     * 省份ID
     */
    private Integer provinceId;

    /**
     * 城市ID
     */
    private Integer cityId;

    /**
     * 用户状态ID
     */
    private Integer userStateId;

    /**
     * 好友策略ID
     */
    private Integer friendshipPolicyId;

    private String friendPolicyQuestion;

    /**
     *  好友策略答案
     */
    private String friendPolicyAnswer;

    /**
     *  好友策略密码
     */
    private String friendPolicyPassword;
}
