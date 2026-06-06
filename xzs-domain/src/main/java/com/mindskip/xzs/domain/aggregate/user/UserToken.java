package com.mindskip.xzs.domain.aggregate.user;

import java.time.LocalDateTime;

public class UserToken {
    private Integer id;
    private Integer userId;
    private String userName;
    private String token;
    private String wxOpenId;
    private LocalDateTime createTime;
    private LocalDateTime expireTime;

    public UserToken() {}

    public UserToken(Integer userId, String token, LocalDateTime expireTime) {
        this.userId = userId;
        this.token = token;
        this.expireTime = expireTime;
        this.createTime = LocalDateTime.now();
    }

    public boolean isExpired() {
        return expireTime != null && LocalDateTime.now().isAfter(expireTime);
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getWxOpenId() { return wxOpenId; }
    public void setWxOpenId(String wxOpenId) { this.wxOpenId = wxOpenId; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public LocalDateTime getExpireTime() { return expireTime; }
    public void setExpireTime(LocalDateTime expireTime) { this.expireTime = expireTime; }
}
