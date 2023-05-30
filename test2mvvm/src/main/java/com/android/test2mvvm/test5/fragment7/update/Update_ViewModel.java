package com.android.test2mvvm.test5.fragment7.update;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.android.test2mvvm.base.BaseViewModel;
import com.android.test2mvvm.test5.fragment7.UserRepository;
import com.android.test2mvvm.test5.fragment7.bean.User;

import java.util.List;

public class Update_ViewModel extends BaseViewModel {
    public LiveData<User> userMutableLiveData;
    UserRepository userRepository;

    public Update_ViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance();
        fail = userRepository.failed;
        userMutableLiveData = Transformations.map(userRepository.getUser01(), new Function<User, User>() {
            @Override
            public User apply(User input) {
                return input;
            }
        });

    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

}
