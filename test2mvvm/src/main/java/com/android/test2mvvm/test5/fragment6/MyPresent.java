package com.android.test2mvvm.test5.fragment6;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.android.test2mvvm.util.Loge;

public class MyPresent implements IPresent {

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        IPresent.super.onCreate(owner);
        Loge.e("onCreate");
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        IPresent.super.onStart(owner);
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        IPresent.super.onResume(owner);
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        IPresent.super.onPause(owner);
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        IPresent.super.onStop(owner);
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        IPresent.super.onDestroy(owner);
        Loge.e("onDestroy");
    }
}
