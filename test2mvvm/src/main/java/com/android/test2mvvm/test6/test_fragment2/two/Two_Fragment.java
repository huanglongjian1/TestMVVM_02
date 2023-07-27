package com.android.test2mvvm.test6.test_fragment2.two;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
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

import java.util.List;
import java.util.Random;

public class Two_Fragment extends Util_BaseFullBottomSheetFragment<TestBottomsheetfragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_bottomsheetfragment;
    }

    // 在Activity中创建Messenger对象，并将其作为参数传递给Service
    Messenger messenger = new Messenger(new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    messengerService = msg.replyTo;
                    break;
                case 2:
                    Bundle bundle = (Bundle) msg.obj;
                    Loge.e(bundle.getString("service", "没有取到服务器消息"));
            }
        }
    });
    Intent intent;
    Messenger messengerService;
    Message message = Message.obtain();


    @Override
    protected void initView() {
        intent = new Intent(getContext(), Two_Service.class);
        intent.putExtra("messenger", messenger);
        getContext().startService(intent);
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("client", "客户端发消息" + new Random().nextInt(10000));
                message.what = 2;
                message.obj = bundle;
                try {
                    messengerService.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e(messenger.toString()+":"+messengerService.toString());
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Loge.e("onDestroy");
        if (isServiceWork(getContext(), Two_Service.class.getName())) {
            getContext().stopService(intent);
        }
        messenger = null;
        messengerService = null;
        message = null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDataLazyLoad() {

    }

    public boolean isServiceWork(Context mContext, String serviceName) {
        boolean isWork = false;
        ActivityManager myAM = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> myList = myAM.getRunningServices(40);
        Loge.e(myList.size() + ":myList");
        if (myList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < myList.size(); i++) {
            String mName = myList.get(i).service.getClassName().toString();
            if (mName.equals(serviceName)) {
                Loge.e(mName);
                isWork = true;
                break;
            }
        }
        return isWork;
    }
}
