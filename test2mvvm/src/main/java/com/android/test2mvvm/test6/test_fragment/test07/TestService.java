package com.android.test2mvvm.test6.test_fragment.test07;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.android.test2mvvm.util.Loge;

import java.util.Random;

public class TestService<T> extends Service {
    boolean isCreate = false;
    T data;

    public void setData(T data) {
        this.data = data;
        Loge.e(data.toString());
    }


    @Override
    public void onCreate() {
        super.onCreate();
        isCreate = true;
    }

    public boolean isCreate() {
        return isCreate;
    }

    public Integer getNum() {
        return new Random().nextInt(Integer.MAX_VALUE);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder {
        public TestService getTestService() {
            return TestService.this;
        }
    }
}
