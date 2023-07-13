package com.android.test2mvvm.test5.fragment4.bean;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Person {
    @NonNull
    @PrimaryKey
    String name = "person";
    String psw;

    public Person() {
    }

    @Override
    public String toString() {
        return "person{" +
                "name='" + name + '\'' +
                ", psw='" + psw + '\'' +
                '}';
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public Person(String name, String psw) {
        this.name = name;
        this.psw = psw;
    }
}
