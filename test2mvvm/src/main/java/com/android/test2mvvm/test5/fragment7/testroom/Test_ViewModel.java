package com.android.test2mvvm.test5.fragment7.testroom;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.android.test2mvvm.base.BaseViewModel;
import com.android.test2mvvm.test5.fragment7.UserRepository;
import com.android.test2mvvm.test5.fragment7.bean.User;
import com.google.gson.Gson;

import java.util.List;


public class Test_ViewModel extends BaseViewModel {
    public MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
    UserRepository userRepository;
    public LiveData<List<User>> roomuser;

    public LiveData<List<User>> singleLiveEvent;


    public Test_ViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance();
        roomuser = userRepository.getUser();
//        singleLiveEvent = Transformations.map(roomuser, new Function<List<User>, List<User>>() {
//            @Override
//            public List<User> apply(List<User> input) {
//                return input;
//            }
//        });
        singleLiveEvent=Transformations.switchMap(roomuser, new Function<List<User>, LiveData<List<User>>>() {
            @Override
            public LiveData<List<User>> apply(List<User> input) {
                SingleLiveEvent<List<User>> listSingleLiveEvent=new SingleLiveEvent<>();
                listSingleLiveEvent.setValue(input);
                return listSingleLiveEvent;
            }
        });
    }


    public void saveUser() {
        fail = userRepository.failed;
        userMutableLiveData.getValue().setUid(1);
        Log.d("TAG", "register: " + new Gson().toJson(userMutableLiveData.getValue()));
        userRepository.saveUser(userMutableLiveData.getValue());
    }
}
