package com.android.test2mvvm.test6.test_fragment.test06;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.android.test2mvvm.test1.fragment8.ExecutorUtil;
import com.android.test2mvvm.util.Loge;

public class MyService extends Service {
    private static final String TAG = "aidltest";
    private IListener listener;
    int anInt = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        Loge.e(String.valueOf(android.os.Process.myPid()) + ":MyService");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return (IBinder) iCallback;
    }

    ICallback iCallback = new ICallback.Stub() {

        @Override
        public void setListener(IListener lst) throws RemoteException {
            listener = lst;
            ExecutorUtil.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            if (listener != null) {
                                listener.sendMsgtoClient("服务器发消息" + anInt++);
                            } else {
                                break;
                            }

                        } catch (RemoteException e) {
                            e.printStackTrace();
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

        @Override
        public void sendMSGtoService(String msg) throws RemoteException {
            Loge.e("来自客户端的消息:" + msg);
        }
    };

    @Override
    public boolean onUnbind(Intent intent) {
        Loge.e("onUnbind");
        listener = null;
        iCallback = null;
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Loge.e("onDestroy");
        super.onDestroy();
        listener = null;
        iCallback = null;
    }
}
