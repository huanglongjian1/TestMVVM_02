package com.android.test2mvvm.test6.fragments.result;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.android.test2mvvm.base.BaseViewModel;

public class ViewModel_AB extends BaseViewModel {
    public MutableLiveData<Integer> num = new MutableLiveData<>();
    public MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();

    public ViewModel_AB(@NonNull Application application) {
        super(application);
        num.setValue(0);
        stringMutableLiveData.setValue("还没有初始化");
    }
}
