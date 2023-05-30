package com.android.test2mvvm.test5.fragment7.register;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.android.test2mvvm.base.BaseViewModel;
import com.android.test2mvvm.test5.fragment7.UserRepository;
import com.android.test2mvvm.test5.fragment7.bean.User;
import com.android.test2mvvm.test5.fragment7.dao.AppDatabase;
import com.android.test2mvvm.test5.fragment7.dao.UserDao;
import com.google.gson.Gson;


public class RegisterViewModel extends BaseViewModel {
    public MutableLiveData<User> user = new MutableLiveData<User>();
    UserRepository userRepository;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        userRepository =UserRepository.getInstance();
    }

    public MutableLiveData<User> getUser() {
        return user;
    }

    /**
     * 注册
     */
    public void register() {
        fail = userRepository.failed;
        user.getValue().setUid(1);
        Log.d("TAG", "register: " + new Gson().toJson(user.getValue()));
        userRepository.saveUser(user.getValue());
    }

}
