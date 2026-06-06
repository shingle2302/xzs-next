package com.mindskip.xzs.domain.aggregate.user;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {
    private Integer id;
    private String userUuid;
    private String userName;
    private String password;
    private String realName;
    private Integer age;
    private Integer sex;
    private LocalDateTime birthDay;
    private Integer userLevel;
    private String phone;
    private String email;
    private Integer role;
    private Integer status;
    private String imagePath;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private LocalDateTime lastActiveTime;
    private Boolean deleted;
    private String wxOpenId;
    private String token;

    public User() {}

    public User(String userName, String password, Integer role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.status = 1;
        this.deleted = false;
        this.createTime = LocalDateTime.now();
    }

    public void changePassword(String newPassword) {
        this.password = Objects.requireNonNull(newPassword);
        this.modifyTime = LocalDateTime.now();
    }

    public void disable() {
        this.status = 2;
        this.modifyTime = LocalDateTime.now();
    }

    public void enable() {
        this.status = 1;
        this.modifyTime = LocalDateTime.now();
    }

    public void bindWx(String openId) {
        this.wxOpenId = openId;
        this.modifyTime = LocalDateTime.now();
    }

    public boolean isAdmin() {
        return role != null && role == 3;
    }

    public boolean isStudent() {
        return role != null && role == 1;
    }

    public boolean isDisabled() {
        return status != null && status == 2;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUserUuid() { return userUuid; }
    public void setUserUuid(String userUuid) { this.userUuid = userUuid; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRealName() { return realName; }
    public void setRealName(String realName) { this.realName = realName; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public Integer getSex() { return sex; }
    public void setSex(Integer sex) { this.sex = sex; }
    public LocalDateTime getBirthDay() { return birthDay; }
    public void setBirthDay(LocalDateTime birthDay) { this.birthDay = birthDay; }
    public Integer getUserLevel() { return userLevel; }
    public void setUserLevel(Integer userLevel) { this.userLevel = userLevel; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Integer getRole() { return role; }
    public void setRole(Integer role) { this.role = role; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public LocalDateTime getModifyTime() { return modifyTime; }
    public void setModifyTime(LocalDateTime modifyTime) { this.modifyTime = modifyTime; }
    public LocalDateTime getLastActiveTime() { return lastActiveTime; }
    public void setLastActiveTime(LocalDateTime lastActiveTime) { this.lastActiveTime = lastActiveTime; }
    public Boolean getDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }
    public String getWxOpenId() { return wxOpenId; }
    public void setWxOpenId(String wxOpenId) { this.wxOpenId = wxOpenId; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
