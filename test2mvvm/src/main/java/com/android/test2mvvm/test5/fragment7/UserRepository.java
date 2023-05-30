package com.android.test2mvvm.test5.fragment7;

import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.test2mvvm.Test2_App;
import com.android.test2mvvm.test5.fragment7.bean.User;
import com.android.test2mvvm.util.ExecutorUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class UserRepository {
    public final MutableLiveData<String> failed = new MutableLiveData<>();
    private final static UserRepository instance = new UserRepository();

    private UserRepository() {

    }

    public static UserRepository getInstance() {
        return instance;
    }

    public void saveUser(User user) {
        ExecutorUtil.execute(new Runnable() {
            @Override
            public void run() {
                Test2_App.getDb().userDao().deleteAll();
                Test2_App.getDb().userDao().insert(user);
                failed.postValue("200");
            }
        });
    }

    /**
     * 更新用户信息
     *
     * @param user
     */
    public void updateUser(User user) {
        ExecutorUtil.execute(new Runnable() {
            @Override
            public void run() {
                Test2_App.getDb().userDao().update(user);
                Log.e("TAG", "updateUser: " + "保存成功");
                failed.postValue("修改user成功");
            }
        });
    }

    public void clearUser() {
        ExecutorUtil.execute(new Runnable() {
            @Override
            public void run() {
                Test2_App.getDb().userDao().deleteAll();
                failed.postValue("清除账号成功");
            }
        });
    }

    public LiveData<List<User>> getUser() {
        return Test2_App.getDb().userDao().getAll();
    }

    public LiveData<User> getUser01() {
        return Test2_App.getDb().userDao().getUser01();
    }

}
