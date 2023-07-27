package com.android.test2mvvm.test6.test_fragment.test09;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.android.test2mvvm.test1.fragment8.ExecutorUtil;
import com.android.test2mvvm.test6.test_fragment.CallBack;
import com.android.test2mvvm.util.Loge;

import java.util.Random;

public class TestSevice extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Loge.e("onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Loge.e("onDestroy");
        unCallBack();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Loge.e("onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Loge.e("onRebind");
    }

    public void getRandomInt() {
        if (callBack != null) {
            callBack.onResult(new Random().nextInt(1000) + "-");
        }
    }

    @Nullable
    @Override

    public IBinder onBind(Intent intent) {
        Loge.e("onBind");
        return new TestBinder();
    }

    CallBack callBack;

    public void setCallBack(CallBack back) {
        callBack = back;
    }

    public void unCallBack() {
        if (callBack != null)
            callBack = null;
    }

    public void start_task(int num) {
        for (int i = 0; i < num; i++) {
            ExecutorUtil.execute(new Runnable() {
                @Override
                public void run() {
                    int i1 = new Random().nextInt(100);
                    try {
                        Thread.sleep(i1 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (callBack != null) callBack.onResult(i1 + ":回调数字");
                }
            });
            Loge.e("执行任务：" + i);
        }
    }

    public class TestBinder extends Binder {
        public TestSevice getTestService() {
            return TestSevice.this;
        }
    }
}
