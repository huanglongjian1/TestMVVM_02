package com.android.test2mvvm.test4.fragment3.dao;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User_bean {
    @PrimaryKey
    int id;
    String name;
    String psw;

    public User_bean(int id, String name, String psw) {
        this.id = id;
        this.name = name;
        this.psw = psw;
    }

    @Override
    public String toString() {
        return "User_bean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", psw='" + psw + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
