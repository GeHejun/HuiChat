package com.ghj.rest.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
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
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键、自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登陆账号
     */
    @TableField("login_name")
    private String loginName;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 密码
     */
    @TableField("pass_word")
    private String passWord;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 性别
     */
    private Boolean sex;

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
    @TableField("head_portrait")
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
    @TableField("blood_type")
    private String bloodType;

    /**
     * 毕业学校
     */
    @TableField("school_tag")
    private String schoolTag;

    /**
     * 职业
     */
    private String vocation;

    /**
     * 国家ID
     */
    @TableField("nation_id")
    private Integer nationId;

    /**
     * 省份ID
     */
    @TableField("province_id")
    private Integer provinceId;

    /**
     * 城市ID
     */
    @TableField("city_id")
    private Integer cityId;

    /**
     * 用户状态ID
     */
    @TableField("user_state_id")
    private Integer userStateId;

    /**
     * 好友策略ID
     */
    @TableField("friendship_policy_id")
    private Integer friendshipPolicyId;

    @TableField("friend_policy_question")
    private String friendPolicyQuestion;

    /**
     *  好友策略答案 
     */
    @TableField("friend_policy_answer")
    private String friendPolicyAnswer;

    /**
     *  好友策略密码
     */
    @TableField("friend_policy_password")
    private String friendPolicyPassword;

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getLoginName() {
        return loginName;
    }

    public User setLoginName(String loginName) {
        this.loginName = loginName;
        return this;
    }
    public String getNickName() {
        return nickName;
    }

    public User setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }
    public String getPassWord() {
        return passWord;
    }

    public User setPassWord(String passWord) {
        this.passWord = passWord;
        return this;
    }
    public String getSignature() {
        return signature;
    }

    public User setSignature(String signature) {
        this.signature = signature;
        return this;
    }
    public Boolean getSex() {
        return sex;
    }

    public User setSex(Boolean sex) {
        this.sex = sex;
        return this;
    }
    public Date getBirthday() {
        return birthday;
    }

    public User setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }
    public String getTelephone() {
        return telephone;
    }

    public User setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }
    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }
    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }
    public String getIntro() {
        return intro;
    }

    public User setIntro(String intro) {
        this.intro = intro;
        return this;
    }
    public String getHeadPortrait() {
        return headPortrait;
    }

    public User setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
        return this;
    }
    public String getZodiac() {
        return zodiac;
    }

    public User setZodiac(String zodiac) {
        this.zodiac = zodiac;
        return this;
    }
    public Integer getAge() {
        return age;
    }

    public User setAge(Integer age) {
        this.age = age;
        return this;
    }
    public String getConstellation() {
        return constellation;
    }

    public User setConstellation(String constellation) {
        this.constellation = constellation;
        return this;
    }
    public String getBloodType() {
        return bloodType;
    }

    public User setBloodType(String bloodType) {
        this.bloodType = bloodType;
        return this;
    }
    public String getSchoolTag() {
        return schoolTag;
    }

    public User setSchoolTag(String schoolTag) {
        this.schoolTag = schoolTag;
        return this;
    }
    public String getVocation() {
        return vocation;
    }

    public User setVocation(String vocation) {
        this.vocation = vocation;
        return this;
    }
    public Integer getNationId() {
        return nationId;
    }

    public User setNationId(Integer nationId) {
        this.nationId = nationId;
        return this;
    }
    public Integer getProvinceId() {
        return provinceId;
    }

    public User setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
        return this;
    }
    public Integer getCityId() {
        return cityId;
    }

    public User setCityId(Integer cityId) {
        this.cityId = cityId;
        return this;
    }
    public Integer getUserStateId() {
        return userStateId;
    }

    public User setUserStateId(Integer userStateId) {
        this.userStateId = userStateId;
        return this;
    }
    public Integer getFriendshipPolicyId() {
        return friendshipPolicyId;
    }

    public User setFriendshipPolicyId(Integer friendshipPolicyId) {
        this.friendshipPolicyId = friendshipPolicyId;
        return this;
    }
    public String getFriendPolicyQuestion() {
        return friendPolicyQuestion;
    }

    public User setFriendPolicyQuestion(String friendPolicyQuestion) {
        this.friendPolicyQuestion = friendPolicyQuestion;
        return this;
    }
    public String getFriendPolicyAnswer() {
        return friendPolicyAnswer;
    }

    public User setFriendPolicyAnswer(String friendPolicyAnswer) {
        this.friendPolicyAnswer = friendPolicyAnswer;
        return this;
    }
    public String getFriendPolicyPassword() {
        return friendPolicyPassword;
    }

    public User setFriendPolicyPassword(String friendPolicyPassword) {
        this.friendPolicyPassword = friendPolicyPassword;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", loginName=" + loginName +
        ", nickName=" + nickName +
        ", passWord=" + passWord +
        ", signature=" + signature +
        ", sex=" + sex +
        ", birthday=" + birthday +
        ", telephone=" + telephone +
        ", name=" + name +
        ", email=" + email +
        ", intro=" + intro +
        ", headPortrait=" + headPortrait +
        ", zodiac=" + zodiac +
        ", age=" + age +
        ", constellation=" + constellation +
        ", bloodType=" + bloodType +
        ", schoolTag=" + schoolTag +
        ", vocation=" + vocation +
        ", nationId=" + nationId +
        ", provinceId=" + provinceId +
        ", cityId=" + cityId +
        ", userStateId=" + userStateId +
        ", friendshipPolicyId=" + friendshipPolicyId +
        ", friendPolicyQuestion=" + friendPolicyQuestion +
        ", friendPolicyAnswer=" + friendPolicyAnswer +
        ", friendPolicyPassword=" + friendPolicyPassword +
        "}";
    }
}
