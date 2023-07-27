package com.android.test2mvvm.test6.test_fragment2.three;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;

import androidx.annotation.NonNull;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

public class F3_Fragment extends Util_BaseFullBottomSheetFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    boolean connect = false;
    Messenger messengerClient = new Messenger(new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle = (Bundle) msg.obj;
            if (msg.what == 2) {
                Loge.e("服务器发过来消息说:" + bundle.getString("service", "没有收到消息"));
            }
        }
    });
    Messenger messengerService;
    Message message = Message.obtain();
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messengerService = new Messenger(service);
            connect = true;
            Bundle bundle = new Bundle();
            bundle.putString("messenger", "接收客户端信使");
            message.obj = bundle;
            message.what = 1;

            message.replyTo = messengerClient;

            try {
                messengerService.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    Intent intent;
    int anInt = 0;

    @Override
    protected void initView() {
        binding.test6FragmentBtn.setText("绑定服务");
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().bindService(intent, serviceConnection, Activity.BIND_AUTO_CREATE);
            }
        });
        binding.test6FragmentBtn2.setText("解除服务");
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (connect) {
                    Bundle bundle = new Bundle();
                    bundle.putString("client", "第二个客户端退出");
                    message.what = 3;
                    message.obj = bundle;
                    message.replyTo = messengerClient;
                    try {
                        messengerService.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    getActivity().unbindService(serviceConnection);
                    connect = false;
                }
            }
        });
        binding.test6FragmentBtn3.setText("发送消息");
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("client", "客户端发消息" + anInt++);
                message.obj = bundle;
                message.what = 2;
                message.arg1 = 2;

                try {
                    messengerService.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void initData() {
        intent = new Intent(getContext(), Three_Service.class);

    }

    @Override
    protected void onDataLazyLoad() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (connect) {
            Bundle bundle = new Bundle();
            bundle.putString("client", "第二个客户端退出");
            message.what = 3;
            message.obj = bundle;
            message.replyTo = messengerClient;
            try {
                messengerService.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            getActivity().unbindService(serviceConnection);
            connect = false;
        }
    }
}
