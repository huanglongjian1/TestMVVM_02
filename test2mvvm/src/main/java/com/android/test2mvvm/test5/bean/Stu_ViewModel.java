package com.android.test2mvvm.test5.bean;

import android.util.Log;

import androidx.databinding.ObservableField;

public class Stu_ViewModel{
    public Student student;
    public ObservableField<Student> userObservableField;

    public Stu_ViewModel() {
        this.student = new Student("Albert Einstein");
        userObservableField = new ObservableField<>();
        userObservableField.set(student);
    }

    //就是获取控件上的输入的值

    public String getUserNameA() {
        return userObservableField.get().stu_name;
    }

    //修改控件上的值
    public void setUserNameA(String userName) {
        Log.d("这是JetPack日志", "username is " + userName);
        userObservableField.set(new Student(userName));


    }

}
