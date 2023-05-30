package com.android.test2mvvm.test5.fragment6.aac;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

public class SaveStateHandle_ViewModel extends AndroidViewModel {
    private SavedStateHandle mSavedStateHandle;
    private static final String KEY = "key";
    private static final String NUM = "num";

    public SaveStateHandle_ViewModel(@NonNull Application application, SavedStateHandle savedStateHandle) {
        super(application);
        mSavedStateHandle = savedStateHandle;
        if (!mSavedStateHandle.contains(KEY)) {
            load();
        }
    }

    public MutableLiveData<Integer> getNum() {
        return mSavedStateHandle.getLiveData(KEY);
    }

    public void load() {
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences(NUM, Context.MODE_PRIVATE);
        int i = sharedPreferences.getInt(KEY, 0);
        mSavedStateHandle.set(KEY, i);
    }

    public void save() {
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences(NUM, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(KEY, getNum().getValue());
        edit.apply();
    }

    public void addNum() {
        getNum().setValue(getNum().getValue() + 1);
    }


}
