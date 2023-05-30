package com.android.test2mvvm.test5.fragment7.testroom;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

public class MainViewModelLiveData extends AndroidViewModel {
    public SavedStateHandle mSavedStateHandle;
    public MutableLiveData<Integer> num;
    public static final String KEY = "key";
    public static final String NUM = "num";

    public MainViewModelLiveData(@NonNull Application application, SavedStateHandle savedStateHandle) {
        super(application);
        mSavedStateHandle = savedStateHandle;
        if (!mSavedStateHandle.contains(KEY)) {
            load();
        }
    }

    public MutableLiveData<Integer> getNum() {
        num = mSavedStateHandle.getLiveData(KEY);
        return num;
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
