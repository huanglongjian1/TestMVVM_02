package com.android.testmvvm.test11.fragment2;

import android.view.View;

import com.android.testmvvm.uitl.Loge;

import javax.inject.Inject;

public class User {
    public final String firstName;
    public final String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public void onSaveClick(){

        Loge.e("绑定监听器。");
    }
}
