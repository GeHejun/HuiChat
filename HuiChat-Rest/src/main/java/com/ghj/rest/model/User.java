package com.ghj.rest.model;

import java.io.Serializable;
import java.util.Date;

/**
 * user
 * @author 
 */
public class User implements Serializable {
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

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getZodiac() {
        return zodiac;
    }

    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getSchoolTag() {
        return schoolTag;
    }

    public void setSchoolTag(String schoolTag) {
        this.schoolTag = schoolTag;
    }

    public String getVocation() {
        return vocation;
    }

    public void setVocation(String vocation) {
        this.vocation = vocation;
    }

    public Integer getNationId() {
        return nationId;
    }

    public void setNationId(Integer nationId) {
        this.nationId = nationId;
    }

    public Integer getprovinceId() {
        return provinceId;
    }

    public void setprovinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getUserStateId() {
        return userStateId;
    }

    public void setUserStateId(Integer userStateId) {
        this.userStateId = userStateId;
    }

    public Integer getFriendshipPolicyId() {
        return friendshipPolicyId;
    }

    public void setFriendshipPolicyId(Integer friendshipPolicyId) {
        this.friendshipPolicyId = friendshipPolicyId;
    }

    public String getFriendPolicyQuestion() {
        return friendPolicyQuestion;
    }

    public void setFriendPolicyQuestion(String friendPolicyQuestion) {
        this.friendPolicyQuestion = friendPolicyQuestion;
    }

    public String getFriendPolicyAnswer() {
        return friendPolicyAnswer;
    }

    public void setFriendPolicyAnswer(String friendPolicyAnswer) {
        this.friendPolicyAnswer = friendPolicyAnswer;
    }

    public String getFriendPolicyPassword() {
        return friendPolicyPassword;
    }

    public void setFriendPolicyPassword(String friendPolicyPassword) {
        this.friendPolicyPassword = friendPolicyPassword;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLoginName() == null ? other.getLoginName() == null : this.getLoginName().equals(other.getLoginName()))
            && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
            && (this.getPassWord() == null ? other.getPassWord() == null : this.getPassWord().equals(other.getPassWord()))
            && (this.getSignature() == null ? other.getSignature() == null : this.getSignature().equals(other.getSignature()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
            && (this.getTelephone() == null ? other.getTelephone() == null : this.getTelephone().equals(other.getTelephone()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getIntro() == null ? other.getIntro() == null : this.getIntro().equals(other.getIntro()))
            && (this.getHeadPortrait() == null ? other.getHeadPortrait() == null : this.getHeadPortrait().equals(other.getHeadPortrait()))
            && (this.getZodiac() == null ? other.getZodiac() == null : this.getZodiac().equals(other.getZodiac()))
            && (this.getAge() == null ? other.getAge() == null : this.getAge().equals(other.getAge()))
            && (this.getConstellation() == null ? other.getConstellation() == null : this.getConstellation().equals(other.getConstellation()))
            && (this.getBloodType() == null ? other.getBloodType() == null : this.getBloodType().equals(other.getBloodType()))
            && (this.getSchoolTag() == null ? other.getSchoolTag() == null : this.getSchoolTag().equals(other.getSchoolTag()))
            && (this.getVocation() == null ? other.getVocation() == null : this.getVocation().equals(other.getVocation()))
            && (this.getNationId() == null ? other.getNationId() == null : this.getNationId().equals(other.getNationId()))
            && (this.getprovinceId() == null ? other.getprovinceId() == null : this.getprovinceId().equals(other.getprovinceId()))
            && (this.getCityId() == null ? other.getCityId() == null : this.getCityId().equals(other.getCityId()))
            && (this.getUserStateId() == null ? other.getUserStateId() == null : this.getUserStateId().equals(other.getUserStateId()))
            && (this.getFriendshipPolicyId() == null ? other.getFriendshipPolicyId() == null : this.getFriendshipPolicyId().equals(other.getFriendshipPolicyId()))
            && (this.getFriendPolicyQuestion() == null ? other.getFriendPolicyQuestion() == null : this.getFriendPolicyQuestion().equals(other.getFriendPolicyQuestion()))
            && (this.getFriendPolicyAnswer() == null ? other.getFriendPolicyAnswer() == null : this.getFriendPolicyAnswer().equals(other.getFriendPolicyAnswer()))
            && (this.getFriendPolicyPassword() == null ? other.getFriendPolicyPassword() == null : this.getFriendPolicyPassword().equals(other.getFriendPolicyPassword()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLoginName() == null) ? 0 : getLoginName().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getPassWord() == null) ? 0 : getPassWord().hashCode());
        result = prime * result + ((getSignature() == null) ? 0 : getSignature().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getTelephone() == null) ? 0 : getTelephone().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getIntro() == null) ? 0 : getIntro().hashCode());
        result = prime * result + ((getHeadPortrait() == null) ? 0 : getHeadPortrait().hashCode());
        result = prime * result + ((getZodiac() == null) ? 0 : getZodiac().hashCode());
        result = prime * result + ((getAge() == null) ? 0 : getAge().hashCode());
        result = prime * result + ((getConstellation() == null) ? 0 : getConstellation().hashCode());
        result = prime * result + ((getBloodType() == null) ? 0 : getBloodType().hashCode());
        result = prime * result + ((getSchoolTag() == null) ? 0 : getSchoolTag().hashCode());
        result = prime * result + ((getVocation() == null) ? 0 : getVocation().hashCode());
        result = prime * result + ((getNationId() == null) ? 0 : getNationId().hashCode());
        result = prime * result + ((getprovinceId() == null) ? 0 : getprovinceId().hashCode());
        result = prime * result + ((getCityId() == null) ? 0 : getCityId().hashCode());
        result = prime * result + ((getUserStateId() == null) ? 0 : getUserStateId().hashCode());
        result = prime * result + ((getFriendshipPolicyId() == null) ? 0 : getFriendshipPolicyId().hashCode());
        result = prime * result + ((getFriendPolicyQuestion() == null) ? 0 : getFriendPolicyQuestion().hashCode());
        result = prime * result + ((getFriendPolicyAnswer() == null) ? 0 : getFriendPolicyAnswer().hashCode());
        result = prime * result + ((getFriendPolicyPassword() == null) ? 0 : getFriendPolicyPassword().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", loginName=").append(loginName);
        sb.append(", nickName=").append(nickName);
        sb.append(", passWord=").append(passWord);
        sb.append(", signature=").append(signature);
        sb.append(", sex=").append(sex);
        sb.append(", birthday=").append(birthday);
        sb.append(", telephone=").append(telephone);
        sb.append(", name=").append(name);
        sb.append(", email=").append(email);
        sb.append(", intro=").append(intro);
        sb.append(", headPortrait=").append(headPortrait);
        sb.append(", zodiac=").append(zodiac);
        sb.append(", age=").append(age);
        sb.append(", constellation=").append(constellation);
        sb.append(", bloodType=").append(bloodType);
        sb.append(", schoolTag=").append(schoolTag);
        sb.append(", vocation=").append(vocation);
        sb.append(", nationId=").append(nationId);
        sb.append(", provinceId=").append(provinceId);
        sb.append(", cityId=").append(cityId);
        sb.append(", userStateId=").append(userStateId);
        sb.append(", friendshipPolicyId=").append(friendshipPolicyId);
        sb.append(", friendPolicyQuestion=").append(friendPolicyQuestion);
        sb.append(", friendPolicyAnswer=").append(friendPolicyAnswer);
        sb.append(", friendPolicyPassword=").append(friendPolicyPassword);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}