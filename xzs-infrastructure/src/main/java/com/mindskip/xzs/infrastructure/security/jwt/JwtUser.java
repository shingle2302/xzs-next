package com.mindskip.xzs.infrastructure.security.jwt;

public class JwtUser {
    private final Integer userId;
    private final String userName;
    private final Integer role;

    public JwtUser(Integer userId, String userName, Integer role) {
        this.userId = userId;
        this.userName = userName;
        this.role = role;
    }

    public Integer getUserId() { return userId; }
    public String getUserName() { return userName; }
    public Integer getRole() { return role; }
}
