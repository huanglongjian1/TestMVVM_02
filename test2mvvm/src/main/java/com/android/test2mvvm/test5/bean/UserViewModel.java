package com.android.test2mvvm.test5.bean;


import android.util.Log;

import androidx.databinding.ObservableField;

public class UserViewModel {
    public ObservableField<Student> observableField;

    public UserViewModel() {
        Student user = new Student("张三");
        observableField = new ObservableField<>();
        observableField.set(user);
    }

    public String getName() {
        return observableField.get().stu_name;
    }

    public void setName(String name) {
        Log.d("print_txt_msg", name);
        observableField.set(new Student(name));
    }
}