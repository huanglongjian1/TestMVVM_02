package com.android.test2mvvm.test6.test_fragment.test07;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.android.test2mvvm.util.Loge;

public class TestService_02 extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Loge.e(String.valueOf(intent.getLongExtra("service_key", 0)));
        return super.onStartCommand(intent, flags, startId);
    }

    public class TestBinder extends Binder {

    }
}
