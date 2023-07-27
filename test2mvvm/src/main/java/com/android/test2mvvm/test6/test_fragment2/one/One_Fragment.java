package com.android.test2mvvm.test6.test_fragment2.one;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;

import androidx.annotation.NonNull;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

public class One_Fragment extends Util_BaseFullBottomSheetFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    public class OneHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 2) {
                Bundle bundle = (Bundle) msg.obj;
                String msgString = bundle.getString("service", "没有取到数据");
                Loge.e(msgString);
            }
        }
    }

    Messenger messengerClient = new Messenger(new OneHandler());
    Messenger messenger;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            connect = true;
            messenger = new Messenger(service);
            Message message = Message.obtain();
            Bundle bundle = new Bundle();
            bundle.putString("messenger", "接收客户端信使");
            message.obj = bundle;
            message.what = 1;

            message.replyTo = messengerClient;

            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            messenger = null;
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (connect) getActivity().unbindService(serviceConnection);
        messenger = null;
        messengerClient=null;
    }

    boolean connect = false;
    int anInt = 0;

    @Override
    protected void initView() {
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MessengerService.class);
                //      intent.setPackage(getActivity().getPackageName());
                getActivity().bindService(intent, serviceConnection, Activity.BIND_AUTO_CREATE);
            }
        });
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putString("client", "客户端发送消息:" + anInt++);
                message.obj = bundle;
                message.what = 2;  //发送消息使用what=2,发送信使使用what=1 区分
                try {
                    messenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void initData() {
        Loge.e(String.valueOf(android.os.Process.myPid()) + ": one");
    }

    @Override
    protected void onDataLazyLoad() {

    }
}
