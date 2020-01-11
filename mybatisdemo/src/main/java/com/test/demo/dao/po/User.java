package com.test.demo.dao.po;


import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer userId;
    private String userNickName;
    private String userName;
    private String userPwd;
    private Integer userPhone;
    private String entryTime;
    private String role;
    private String manageLocate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getManageLocate() {
        return manageLocate;
    }

    public void setManageLocate(String manageLocate) {
        this.manageLocate = manageLocate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Integer getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Integer userPhone) {
        this.userPhone = userPhone;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}