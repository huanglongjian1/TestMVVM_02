package com.android.test2mvvm.test3;

import com.android.test2mvvm.util.Loge;

import kotlin.jvm.Synchronized;

public class testSY {
    public volatile int i = 0;

    public synchronized void _testSY() {
        i++;
        Loge.e(Thread.currentThread().getName());
    }
}
