package com.iolll.liubo.mvppp_simple.model.net;

/**
 * Created by LiuBo on 2018/10/8.
 */
public class User {
    private int userId;
    private String token;
    private String passWord;
    private String userName;

    public User(String name, String pwd) {
        userName = name ;
        passWord = pwd;
    }



    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTokenForNet() {
        return getToken() + "_" + getUserId();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", token='" + token + '\'' +
                ", passWord='" + passWord + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
