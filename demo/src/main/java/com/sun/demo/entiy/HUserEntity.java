package com.sun.demo.entiy;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "h_user", schema = "bootest", catalog = "")
public class HUserEntity {
    private long userId;
    private String userName;
    private String password;
    private String intro;
    private String nickName;
    private String headImg;
    private String sex;
    private Date birth;
    private String horoscope;
    private Long focus;
    private Long follower;
    private Long popularity;
    private Long level;
    private Long inviterId;
    private Timestamp lastLogin;
    private Integer type;
    private String email;
    private Timestamp createTime;
    private String createUser;
    private Timestamp updateTime;
    private String updateUser;
    private String status;

    @Id
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name", nullable = true, length = 64)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 64)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "intro", nullable = true, length = 512)
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Basic
    @Column(name = "nick_name", nullable = true, length = 64)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Basic
    @Column(name = "head_img", nullable = true, length = 256)
    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
    @Basic
    @Column(name = "sex", nullable = true, length = 1,columnDefinition ="char" )
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "birth", nullable = true)
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Basic
    @Column(name = "horoscope", nullable = true, length = 32)
    public String getHoroscope() {
        return horoscope;
    }

    public void setHoroscope(String horoscope) {
        this.horoscope = horoscope;
    }

    @Basic
    @Column(name = "focus", nullable = true)
    public Long getFocus() {
        return focus;
    }

    public void setFocus(Long focus) {
        this.focus = focus;
    }

    @Basic
    @Column(name = "follower", nullable = true)
    public Long getFollower() {
        return follower;
    }

    public void setFollower(Long follower) {
        this.follower = follower;
    }

    @Basic
    @Column(name = "popularity", nullable = true)
    public Long getPopularity() {
        return popularity;
    }

    public void setPopularity(Long popularity) {
        this.popularity = popularity;
    }

    @Basic
    @Column(name = "level", nullable = true)
    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    @Basic
    @Column(name = "inviter_id", nullable = true)
    public Long getInviterId() {
        return inviterId;
    }

    public void setInviterId(Long inviterId) {
        this.inviterId = inviterId;
    }

    @Basic
    @Column(name = "last_login", nullable = true)
    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 128)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "create_user", nullable = true, length = 64)
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Basic
    @Column(name = "update_time", nullable = true)
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "update_user", nullable = true, length = 64)
    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 1,columnDefinition ="char")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HUserEntity that = (HUserEntity) o;
        return userId == that.userId &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(intro, that.intro) &&
                Objects.equals(nickName, that.nickName) &&
                Objects.equals(headImg, that.headImg) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(birth, that.birth) &&
                Objects.equals(horoscope, that.horoscope) &&
                Objects.equals(focus, that.focus) &&
                Objects.equals(follower, that.follower) &&
                Objects.equals(popularity, that.popularity) &&
                Objects.equals(level, that.level) &&
                Objects.equals(inviterId, that.inviterId) &&
                Objects.equals(lastLogin, that.lastLogin) &&
                Objects.equals(type, that.type) &&
                Objects.equals(email, that.email) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(createUser, that.createUser) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(updateUser, that.updateUser) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, password, intro, nickName, headImg, sex, birth, horoscope, focus, follower, popularity, level, inviterId, lastLogin, type, email, createTime, createUser, updateTime, updateUser, status);
    }
}
