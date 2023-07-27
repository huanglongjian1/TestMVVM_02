package com.android.test2mvvm.test6.test_fragment2.three;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.test2mvvm.R;
import com.android.test2mvvm.util.ExecutorUtil;
import com.android.test2mvvm.util.Loge;

import java.util.ArrayList;
import java.util.List;

public class Three_Service extends Service {
    Messenger messengerService = new Messenger(new ServiceHandler());
    List<Messenger> messengerList = new ArrayList<>();

    public class ServiceHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle = (Bundle) msg.obj;
            switch (msg.what) {
                case 1:
                    Messenger messengerClient = msg.replyTo;
                    messengerList.add(messengerClient);
                    Loge.e(bundle.getString("messenger", "没得数据"));
                    break;
                case 2:
                    Loge.e("第" + String.valueOf(msg.arg1) + "个客户端说：" + bundle.getString("client", "遗憾，没取到消息"));
                    break;
                case 3:
                    Messenger messenger = msg.replyTo;
                    Loge.e(messengerList.size() + ":客户端数量");
                    messengerList.remove(messenger);
                    Loge.e(messengerList.size() + ":客户端数量");
                    Loge.e(bundle.getString("client", "null"));
                    break;
                case 4:
//                    Notification.Builder localBuilder = new Notification.Builder(getApplicationContext());
//                    localBuilder.setSmallIcon(R.drawable.ic_loading);
//                    localBuilder.setContentTitle("Service标题");
//                    localBuilder.setContentText("正在运行...");
//                    startForeground(1, localBuilder.getNotification());
                    Toast.makeText(getApplicationContext(),bundle.getString("client","没有取到消息"),Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Loge.e("Three_service-----onBind");
        return messengerService.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {

        return super.onUnbind(intent);
    }

    Message message = Message.obtain();

    public void serviceSentMsg_to_Client(String msg) {
        Bundle bundle = new Bundle();
        bundle.putString("service", msg);
        message.what = 2;
        message.obj = bundle;
        for (Messenger messenger : messengerList) {
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    int anInt = 0;

    @Override
    public void onCreate() {
        Loge.e("Three_service-----onCreate" + android.os.Process.myPid());
        super.onCreate();
        ExecutorUtil.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    anInt++;
                    if (messengerList.size() > 0) {
                        serviceSentMsg_to_Client("服务器广播消息" + anInt);
                    }

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Loge.e("Three_service-----onDestroy");
        messengerService = null;
        messengerList.clear();
    }
}
