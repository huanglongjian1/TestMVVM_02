package com.android.testmvvm;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import com.android.testmvvm.rxjavaroom.DatabaseInitialize;


public class MyAPP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseInitialize.init(this);
    }
}
