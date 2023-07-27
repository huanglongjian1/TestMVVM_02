package com.android.test2mvvm.test6.test_fragment2.one;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.test2mvvm.util.ExecutorUtil;
import com.android.test2mvvm.util.Loge;


public class MessengerService extends Service {
    Messenger messenger = new Messenger(new MessengerHandler());
    int anInt = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        Loge.e("onCreate");
        Loge.e(String.valueOf(android.os.Process.myPid()) + ": onCreate");
        ExecutorUtil.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (messengerClient != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("service", "服务器发消息" + anInt++);
                        Message message = Message.obtain();
                        message.obj = bundle;
                        message.what = 2;
                        try {
                            messengerClient.send(message);
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

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Loge.e("onDestroy");
        messenger = null;
        messengerClient = null;
    }

    Messenger messengerClient;

    public class MessengerHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Bundle bundle = (Bundle) msg.obj;
                    messengerClient = msg.replyTo;
                    Loge.e(bundle.getString("messenger", "没得数据"));
                    break;
                case 2:
                    Bundle bundle1 = (Bundle) msg.obj;
                    Loge.e(bundle1.getString("client", "没有取到客户端发来信息"));
                    break;
                default:
                    break;
            }
        }
    }

}
