package com.android.test2mvvm.test1.fragment8;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.test2mvvm.test1.fragment8.bean.User;
import com.android.test2mvvm.util.Loge;

import java.util.List;

import io.reactivex.rxjava3.functions.Consumer;

public class UserViewModel extends AndroidViewModel {
    UserRepository userRepository;
    public MutableLiveData<String> content = new MutableLiveData<>();
    public MutableLiveData<Boolean> showLoading = new MutableLiveData<Boolean>();

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);

    }

    public void setData() {
        showLoading.setValue(true);
        userRepository.saveData(content.getValue()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Loge.e("保存内容" + s);
                showLoading.setValue(false);
            }
        });
    }

    public void getData() {
        showLoading.setValue(true);
        userRepository.loadData().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                content.setValue(s);
                showLoading.setValue(false);
            }
        });
    }

    public void addUser(User user) {
        userRepository.insert(user);
    }

    public void delete_all() {
        userRepository.clear_all();
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public LiveData<List<User>> getmAllUsers() {


        return userRepository.getmAllUsers();
    }

}
