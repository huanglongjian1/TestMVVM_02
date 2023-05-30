package com.android.test2mvvm.test5.fragment6.aac;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class AccLifecycleObserver implements LifecycleObserver {
    private Context context;
    private String tag;

    AccLifecycleObserver(Context context) {
        this.context = context;
        tag = context.getClass().getSimpleName();
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        Log.i(tag, "onCreate-----" + context.getClass());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        Log.i(tag, "onStart-----" + context.getClass());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.i(tag, "onResume-----" + context.getClass());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.i(tag, "onPause-----" + context.getClass());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Log.i(tag, "onStop-----" + context.getClass());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        Log.i(tag, "onDestroy-----" + context.getClass());
    }
}