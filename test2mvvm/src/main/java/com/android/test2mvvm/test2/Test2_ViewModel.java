package com.android.test2mvvm.test2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.test2mvvm.Test2_App;
import com.android.test2mvvm.base.ApiTransformer;
import com.android.test2mvvm.base.BaseObserver;
import com.android.test2mvvm.base.BaseViewModel;
import com.android.test2mvvm.test2.api.ApiService;
import com.android.test2mvvm.test2.bean.zhuangbi_bean;
import com.android.test2mvvm.util.Loge;
import com.android.test2mvvm.util.retrofit.RetrofitManager;

import java.util.List;


public class Test2_ViewModel extends BaseViewModel {
    public MutableLiveData<Boolean> visibility = new MutableLiveData<Boolean>();
    public MutableLiveData<List<zhuangbi_bean>> zhuangbi_beanMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<zhuangbi_bean>> getZhuangbi_beanMutableLiveData() {
        return zhuangbi_beanMutableLiveData;
    }

    public MutableLiveData<String> getStringMutableLiveData() {
        return stringMutableLiveData;
    }

    @ViewModelInject
    public Test2_ViewModel(Application application) {
        super(application);
        visibility.postValue(true);


    }

    public void get_Zhuangbi() {
        RetrofitManager.get(ApiService.URL_ZHUANGBI).create(ApiService.class)
                .get_zhuangbi_Observable("在下")
                .compose(ApiTransformer.applySchedule())
                .subscribe(new BaseObserver<List<zhuangbi_bean>>() {
                    @Override
                    public void onSuccess(List<zhuangbi_bean> zhuangbi_beans) {
                        zhuangbi_beanMutableLiveData.postValue(zhuangbi_beans);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        fail.postValue(e.getMessage());
                    }
                });
    }

    public void get_baidu() {
        RetrofitManager.get(ApiService.URL_BAIDU).create(ApiService.class).get_baidu()
                .compose(ApiTransformer.applySchedule())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onSuccess(String s) {
                        stringMutableLiveData.postValue(s);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        fail.postValue(e.getMessage());
                    }
                });
    }
}
