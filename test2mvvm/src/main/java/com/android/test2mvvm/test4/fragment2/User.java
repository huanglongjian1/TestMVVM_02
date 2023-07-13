package com.android.test2mvvm.test4.fragment2;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.android.test2mvvm.BR;

public class User extends BaseObservable {
    String name;
    String password;

    public User(String name, int password) {
        this.name = name;
        this.password = String.valueOf(password);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
