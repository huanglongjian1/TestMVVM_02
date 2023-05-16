package com.android.test2mvvm.test1.fragment8;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.test2mvvm.test1.fragment8.bean.User;
import com.android.test2mvvm.test1.fragment8.bean.UserDao;
import com.android.test2mvvm.test1.fragment8.bean.UserDatabase;
import com.android.test2mvvm.test1.fragment8.network.HttpResult;
import com.android.test2mvvm.test1.fragment8.network.NetWork;
import com.android.test2mvvm.util.Loge;

import java.util.List;
import java.util.Random;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> mAllUsers;
    private UserDatabase db;

    private NetWork mNetWork;
    public UserRepository(Context application) {
        db = UserDatabase.getInstance(application);
        userDao = db.getUserDao();


        this.mNetWork = new NetWork((Application) application);

    }

    public Observable<String> saveData(String content) {
        return mNetWork.saveData(content)
                .map(new Function<HttpResult<String>, String>() {
                    @Override
                    public String apply(HttpResult<String> result) {
                       Loge.e(result.toString());
                        return result.data;
                    }
                });
    }

    public Observable<String> loadData() {
        return mNetWork.loadData()
                .map(new Function<HttpResult<String>, String>() {
                    @Override
                    public String apply(HttpResult<String> result) {
                        Loge.e("加载内容"+result.toString());
                        return result.data;
                    }
                });
    }

    public LiveData<List<User>> getmAllUsers() {

        return userDao.getUserLiveData();
    }

    public void insert(User user) {
        ExecutorUtil.execute(new Runnable() {
            @Override
            public void run() {
                userDao.insert(user);
            }
        });

    }

    public void delete(User user) {
        ExecutorUtil.execute(new Runnable() {
            @Override
            public void run() {
                userDao.delete(user);
            }
        });
    }

    public void clear_all() {
        ExecutorUtil.execute(new Runnable() {
            @Override
            public void run() {
                userDao.deleteAll();
            }
        });
    }
}
