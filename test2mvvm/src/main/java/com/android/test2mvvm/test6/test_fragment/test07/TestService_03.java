package com.android.test2mvvm.test6.test_fragment.test07;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.android.test2mvvm.test6.test_fragment.CallBack;
import com.android.test2mvvm.util.ExecutorUtil;
import com.android.test2mvvm.util.Loge;

import java.util.Random;

public class TestService_03 extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Loge.e("onBind");
        return new Test03Binder();
    }

    public class Test03Binder extends Binder {
        public TestService_03 getService() {
            return TestService_03.this;
        }
    }

    CallBack back;

    public void setCallBack(CallBack callBack) {
        back = callBack;
    }

    public void unCallBack() {
        if (back != null) {
            back = null;
        }
    }

    public void start_task(int num) {
        isruning = true;
        for (int i = 0; i < num; i++) {
            ExecutorUtil.execute(new Runnable() {
                @Override
                public void run() {
                      Integer integer = new Random().nextInt(20);
                      try {
                          Thread.sleep(integer * 1000);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }

                      if (back != null) {
                          back.onResult(String.valueOf(integer));
                      }

                }
            });
            Loge.e("======" + i);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Loge.e("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Loge.e("onCreate");
    }

    boolean isruning = false;

    @Override
    public void onDestroy() {
        super.onDestroy();
        isruning = false;
        Loge.e("TestService_03_onDestroy");
    }
}

