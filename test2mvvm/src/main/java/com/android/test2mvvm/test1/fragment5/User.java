package com.android.test2mvvm.test1.fragment5;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.android.test2mvvm.BR;

public class User extends BaseObservable {

    private String nickName;
    private String userface;
    private String userName;
    public String age;

    public User(String nickName, String userface, String userName, String age) {
        this.nickName = nickName;
        this.userface = userface;
        this.userName = userName;
        this.age = age;
    }

    public User() {
    }

    @Bindable
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
        notifyPropertyChanged(BR.nickName);
    }

    @Bindable
    public String getUserface() {
        return userface;
    }

    public void setUserface(String userface) {
        this.userface = userface;
        notifyPropertyChanged(BR.userface);
    }

    @Bindable
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }


    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Override
    public String toString() {
        return "User{" +
                "nickName='" + nickName + '\'' +
                ", userface='" + userface + '\'' +
                ", userName='" + userName + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}