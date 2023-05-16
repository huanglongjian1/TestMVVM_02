package com.android.test2mvvm.test1.fragment3;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public class MainViewModel extends AndroidViewModel {

    private MainRepository mRepository;
    public MutableLiveData<String> content = new MutableLiveData<>();
    public MutableLiveData<Boolean> showLoading = new MutableLiveData(); // 是否显示加载圈
    public MutableLiveData<String> now_time = new MutableLiveData<>();
    public MutableLiveData<HttpResult<String>> httpResultMutableLiveData = new MutableLiveData<>();

    @Inject
    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepository = new MainRepository(application);
    }

    public void setData() {
        if (TextUtils.isEmpty(content.getValue()))return;
        showLoading.setValue(true);
        mRepository.saveData(content.getValue()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                showLoading.setValue(false);
            }
        });
    }

    public MutableLiveData<HttpResult<String>> getHttpResultMutableLiveData() {
        return httpResultMutableLiveData;
    }


    public MutableLiveData<String> getNow_time() {
        return now_time;
    }

    public void getData() {
        showLoading.setValue(true);
        mRepository.loadData().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                content.setValue(s);
                showLoading.setValue(false);
            }
        });
    }
}
