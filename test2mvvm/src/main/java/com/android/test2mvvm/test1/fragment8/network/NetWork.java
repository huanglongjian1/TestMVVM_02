package com.android.test2mvvm.test1.fragment8.network;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NetWork {

    private Application mApplication;

    public NetWork(Application application) {
        this.mApplication = application;
    }

    public Observable<HttpResult<String>> saveData(final String content) {
        return Observable.create(
                        new ObservableOnSubscribe<HttpResult<String>>() {
                            @Override
                            public void subscribe(ObservableEmitter<HttpResult<String>> e) {
                                SharedPreferences sp = mApplication.getSharedPreferences("demo", Context.MODE_PRIVATE);
                                boolean success = sp.edit().putString("content", content).commit();
                                HttpResult httpResult = new HttpResult();
                                if (success) {
                                    httpResult.status = 200;
                                    httpResult.msg = "保存成功";
                                } else {
                                    httpResult.status = 5;
                                    httpResult.msg = "保存失败";
                                }
                                httpResult.data = content;
                                e.onNext(httpResult);
                                e.onComplete();
                            }
                        }
                )
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<HttpResult<String>> loadData() {
        return Observable.create(
                        new ObservableOnSubscribe<HttpResult<String>>() {
                            @Override
                            public void subscribe(ObservableEmitter<HttpResult<String>> e) {
                                SharedPreferences sp = mApplication.getSharedPreferences("demo", Context.MODE_PRIVATE);
                                HttpResult httpResult = new HttpResult();
                                httpResult.data = sp.getString("content", "");
                                httpResult.status = 200;
                                httpResult.msg = "获取成功";
                                e.onNext(httpResult);
                                e.onComplete();
                            }
                        }
                )
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
