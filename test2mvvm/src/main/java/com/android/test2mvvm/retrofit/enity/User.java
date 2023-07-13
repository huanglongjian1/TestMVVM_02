package com.android.test2mvvm.retrofit.enity;

public class User {
    String name;
    String psw;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public User(String name, String psw) {
        this.name = name;
        this.psw = psw;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", psw='" + psw + '\'' +
                '}';
    }
}
