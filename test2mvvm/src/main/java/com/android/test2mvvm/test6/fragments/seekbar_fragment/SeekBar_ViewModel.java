package com.android.test2mvvm.test6.fragments.seekbar_fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.android.test2mvvm.base.BaseViewModel;

public class SeekBar_ViewModel extends BaseViewModel {
    public MutableLiveData<Integer> integerMutableLiveData = new MutableLiveData<>();

    public SeekBar_ViewModel(@NonNull Application application) {
        super(application);
        integerMutableLiveData.setValue(0);
    }

    public MutableLiveData<Integer> getIntegerMutableLiveData() {
        return integerMutableLiveData;
    }
}
