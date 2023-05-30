package com.android.test2mvvm.test5.fragment7.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.android.test2mvvm.base.BaseViewModel;
import com.android.test2mvvm.test5.fragment7.UserRepository;
import com.android.test2mvvm.test5.fragment7.bean.User;

import java.util.List;

public class Main_ViewModel extends BaseViewModel {
    UserRepository userRepository;
    public LiveData<List<User>> localUser;

    public Main_ViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance();

    }

    public void clear() {
        userRepository.clearUser();
    }

    public LiveData<List<User>> getUser() {
        localUser = userRepository.getUser();
        return localUser;
    }

}
