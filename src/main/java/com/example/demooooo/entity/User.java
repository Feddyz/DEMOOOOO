package com.example.demooooo.entity;

import java.util.Date;

public class User {
    private Date updateTime;
    private int state;
    private String nickname;
    private String salt;
    private String password;
    private String phone;
    private long userId;
    private Date creatTime;


    public User(long userId, String phone, String password, String salt, String nickname, Integer state, Date creatTime, Date updateTime) {
        this.userId = userId;
        this.phone = phone;
        this.password = password;
        this.salt = salt;
        this.nickname = nickname;
        this.state = state;
        this.creatTime = creatTime;
        this.updateTime = updateTime;
    }

    public User() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
