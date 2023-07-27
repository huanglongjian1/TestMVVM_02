package com.android.test2mvvm.test6.test_fragment.test10;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.basebottomsheet.BaseFullBottomSheetFragment;
import com.android.test2mvvm.test6.test_fragment.CallBack;
import com.android.test2mvvm.util.Loge;

import java.util.List;

public class Fragment10 extends BaseFullBottomSheetFragment {
    TestBottomsheetfragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = TestBottomsheetfragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    CallBack callBack = new CallBack() {
        @Override
        public void onResult(String result) {
            Loge.e(Thread.currentThread().getName());
            Loge.e(result);
            Message message = handler.obtainMessage();
            message.obj = result;
            message.what = 1;
          //  handler.sendMessage(message);
        }
    };
    public static Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    Loge.e(Thread.currentThread().getName());
                    Loge.e(msg.obj.toString());
                    break;
            }
            return false;
        }
    });
    boolean is_bindService = false;
    TimerService timerService;
    Intent intent;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            TimerService.TimerBinder timerBinder = (TimerService.TimerBinder) service;
            timerService = timerBinder.getService();
            timerService.getTimer();
            timerService.setCallBack(callBack);
            is_bindService = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intent = new Intent(getActivity(), TimerService.class);
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
                if (isServiceWork(getActivity(), TimerService.class.getName())) {
                    getActivity().unbindService(serviceConnection);
                } else {
                    Loge.e("还没有绑定服务");
                }
            }
        });
        binding.test6FragmentBtn3.setText("取消监听");
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isServiceWork(getActivity(), TimerService.class.getName())) {
                    Loge.e("还没有绑定服务");
                    return;
                }
                if (timerService == null) return;
                if (is_bindService) {
                    timerService.unCallBack();
                    is_bindService = false;
                    binding.test6FragmentBtn3.setText("设置监听");
                } else {
                    timerService.setCallBack(callBack);
                    is_bindService = true;
                    binding.test6FragmentBtn3.setText("取消监听");
                }
            }
        });
        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerService.getNum();
            }
        });
        binding.test6FragmentBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerService.getString();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isServiceWork(getActivity(), TimerService.class.getName())) {
            getActivity().unbindService(serviceConnection);

        }
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
