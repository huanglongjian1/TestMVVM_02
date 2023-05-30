package com.android.test2mvvm.test5.fragment6.startup;

import android.content.ContentProvider;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;

import com.android.test2mvvm.util.Loge;

import java.util.List;

public class SDKInitializer<T extends ContentProvider> implements Initializer {
    @NonNull
    @Override
    public Object create(@NonNull Context context) {
       Loge.e("Sdk A 初始化");
        return null;
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return null;
    }
}
