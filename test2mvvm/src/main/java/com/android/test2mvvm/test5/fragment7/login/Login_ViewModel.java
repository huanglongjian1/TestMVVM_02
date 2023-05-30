package com.android.test2mvvm.test5.fragment7.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.test2mvvm.base.BaseViewModel;
import com.android.test2mvvm.test5.fragment7.UserRepository;
import com.android.test2mvvm.test5.fragment7.bean.User;
import com.android.test2mvvm.test5.fragment7.bean.User_login;

import java.util.List;

public class Login_ViewModel extends BaseViewModel {
    public MutableLiveData<User_login> user_loginMutableLiveData = new MutableLiveData<>();
    public LiveData<List<User>> localUser;
    UserRepository userRepository;


    public void getLocalUser() {
        localUser = userRepository.getUser();
        fail = userRepository.failed;
    }

    public Login_ViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance();
        getLocalUser();
    }


}
