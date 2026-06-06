package com.mindskip.xzs.domain.event;

public class UserRegisteredEvent {
    private final Integer userId;
    private final String userName;

    public UserRegisteredEvent(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public Integer getUserId() { return userId; }
    public String getUserName() { return userName; }
}
