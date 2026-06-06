package com.mindskip.xzs.adapter.dto.admin.user;

import com.mindskip.xzs.adapter.dto.common.PageRequest;

public class UserPageRequest extends PageRequest {
    private String userName;
    private Integer role;

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public Integer getRole() { return role; }
    public void setRole(Integer role) { this.role = role; }
}
