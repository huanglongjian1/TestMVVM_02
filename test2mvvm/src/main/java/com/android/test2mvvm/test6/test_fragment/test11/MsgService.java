package com.android.test2mvvm.test6.test_fragment.test11;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.android.test2mvvm.util.Loge;

public class MsgService extends Service {

    @Override
    public void onCreate() {
        Loge.e("onCreate");
        isruning = true;
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;

                while (isruning) {
                    Intent intent = new Intent("MsgReceiver");
                    intent.putExtra("key", progress++);
                    intent.setPackage(getPackageName());
               //     sendBroadcast(intent);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isruning = false;
        Loge.e("onDestroy");
    }

    boolean isruning = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
