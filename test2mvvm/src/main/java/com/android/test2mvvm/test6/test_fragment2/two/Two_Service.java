package com.android.test2mvvm.test6.test_fragment2.two;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.test2mvvm.test1.fragment8.ExecutorUtil;
import com.android.test2mvvm.util.Loge;

public class Two_Service extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Loge.e("onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        messenger = (Messenger) intent.getParcelableExtra("messenger");
        Loge.e("onStartCommand" + messenger.toString());
        message.what = 1;
        message.replyTo = messengerService;
        try {
            messenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        ExecutorUtil.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (messenger != null) {
                        Loge.e("onCreate" + messenger.toString());
                        Bundle bundle = new Bundle();
                        bundle.putString("service", "服务器的消息" + anInt++);
                        message.what = 2;
                        message.obj = bundle;
                        try {
                            messenger.send(message);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Loge.e("onDestroy");
        messenger = null;
        messengerService = null;
        message = null;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    int anInt = 0;
    Message message = Message.obtain();

    Messenger messenger;
    Messenger messengerService = new Messenger(new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 2) {
                Bundle bundle = (Bundle) msg.obj;
                Loge.e(bundle.getString("client", "没有取到客户端的消息"));
            }
        }
    });


}
