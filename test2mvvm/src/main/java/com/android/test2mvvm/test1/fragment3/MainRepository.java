package com.android.test2mvvm.test1.fragment3;

import android.app.Application;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;

public class MainRepository {
    private NetWork mNetWork;
    private Application mApplication;

    @Inject
    public MainRepository(Application application) {
        this.mApplication = application;
        this.mNetWork = new NetWork(application);
    }

    public Observable<String> saveData(String content) {
        return mNetWork.saveData(content)
                .map(new Function<HttpResult<String>, String>() {
                    @Override
                    public String apply(HttpResult<String> result) {
                        return result.data;
                    }
                });
    }

    public Observable<String> loadData() {
        return mNetWork.loadData()
                .map(new Function<HttpResult<String>, String>() {
                    @Override
                    public String apply(HttpResult<String> result) {
                        return result.data;
                    }
                });
    }
}
