package com.mindskip.xzs.adapter.dto.student.user;

public class UserUpdateRequest {
    private String realName;
    private String phone;
    private String email;
    private Integer age;
    private Integer sex;
    private String birthDay;

    public String getRealName() { return realName; }
    public void setRealName(String realName) { this.realName = realName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public Integer getSex() { return sex; }
    public void setSex(Integer sex) { this.sex = sex; }
    public String getBirthDay() { return birthDay; }
    public void setBirthDay(String birthDay) { this.birthDay = birthDay; }
}
