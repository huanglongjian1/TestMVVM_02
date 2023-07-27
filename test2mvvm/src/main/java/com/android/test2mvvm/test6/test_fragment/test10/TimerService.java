package com.android.test2mvvm.test6.test_fragment.test10;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.android.test2mvvm.test6.test_fragment.CallBack;
import com.android.test2mvvm.util.Loge;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TimerService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
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
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            //   Loge.e(Thread.currentThread().getName() + "---");
            if (callBack != null) {

            }
            //  callBack.onResult(String.valueOf(System.currentTimeMillis()));
        }
    };
    Timer timer;

    public void getTimer() {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(timerTask, 0, 2000);
        }
    }

    int anInt = 0;
    String string;

    public void getNum() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    string = ++anInt + "-=====-";
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void getString() {
        callBack.onResult(string);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new TimerBinder();
    }

    CallBack callBack;

    public void setCallBack(CallBack back) {
        callBack = back;
    }

    public void unCallBack() {
        if (callBack != null) {
            callBack = null;
        }
    }

    public class TimerBinder extends Binder {
        public TimerService getService() {
            return TimerService.this;
        }
    }
}
