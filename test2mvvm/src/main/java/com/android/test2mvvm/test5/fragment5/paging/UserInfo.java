package com.android.test2mvvm.test5.fragment5.paging;

public class UserInfo {

    public int userId;
    public String userName;
    public String userAvatar;

    public UserInfo(int userId, String userName, String userAvatar) {
        this.userId = userId;
        this.userName = userName;
        this.userAvatar = userAvatar;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                '}';
    }
}
