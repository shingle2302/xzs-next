package com.mindskip.xzs.adapter.dto.auth;

public class LoginResponse {
    private String token;
    private UserInfo userInfo;

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public UserInfo getUserInfo() { return userInfo; }
    public void setUserInfo(UserInfo userInfo) { this.userInfo = userInfo; }

    public static class UserInfo {
        private Integer id;
        private String userName;
        private String realName;
        private Integer role;
        private String imagePath;

        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }
        public String getUserName() { return userName; }
        public void setUserName(String userName) { this.userName = userName; }
        public String getRealName() { return realName; }
        public void setRealName(String realName) { this.realName = realName; }
        public Integer getRole() { return role; }
        public void setRole(Integer role) { this.role = role; }
        public String getImagePath() { return imagePath; }
        public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    }
}
