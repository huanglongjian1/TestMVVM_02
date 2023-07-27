package com.android.test2mvvm.test6.test_fragment.test11;


import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import java.security.Provider;

public class MessageService extends Service {
    private MessageReciever mReceiver;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
    @Override
    public void onCreate() {
        mReceiver = new MessageReciever();
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(mReceiver, filter);
    }
    @Override
    public void onDestroy() {
        if(null != mReceiver)
        {
            unregisterReceiver(mReceiver);
            mReceiver = null;
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}

