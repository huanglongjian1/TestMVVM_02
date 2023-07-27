package com.android.test2mvvm.test6.test_fragment.test09;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
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

public class Fragment_09 extends BaseFullBottomSheetFragment {
    TestBottomsheetfragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = TestBottomsheetfragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intent = new Intent(getActivity(), TestSevice.class);
        binding.test6FragmentBtn.setText("开启服务");
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isServiceWork(getActivity(), TestSevice.class.getName())) {
                    getActivity().startService(intent);
                }
            }
        });
        binding.test6FragmentBtn2.setText("停止服务");
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isServiceWork(getActivity(), TestSevice.class.getName())) {
                    getActivity().stopService(intent);
                }
            }
        });
        binding.test6FragmentBtn3.setText("绑定服务");
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_bindService = true;
                getActivity().bindService(intent, serviceConnection, Activity.BIND_AUTO_CREATE);
            }
        });
        binding.test6FragmentBtn4.setText("解除服务");
        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isServiceWork(getActivity(), TestSevice.class.getName()) && is_bindService) {
                    getActivity().unbindService(serviceConnection);
                    testBinder = null;
                    is_bindService = false;
                }
            }
        });
        binding.test6FragmentBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (testSevice != null) {
                   testSevice.getRandomInt();
                }
            }
        });
        binding.test6FragmentBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (testSevice != null) {
                    testSevice.start_task(100);
                }
            }
        });
        binding.test6FragmentBtn7.setText("点击取消监听");
        binding.test6FragmentBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (testSevice == null) return;
                if (is_callback) {
                    testSevice.unCallBack();
                    binding.test6FragmentBtn7.setText("点击设置监听");
                    is_callback = false;
                } else {
                    testSevice.setCallBack(callBack);
                    is_callback = true;
                    binding.test6FragmentBtn7.setText("点击取消监听");
                }

            }
        });
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

    CallBack callBack = new CallBack() {
        @Override
        public void onResult(String result) {
            Loge.e(result);
        }
    };

    boolean is_callback = false;
    boolean is_bindService = false;
    Intent intent;
    TestSevice testSevice;
    TestSevice.TestBinder testBinder;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            testBinder = (TestSevice.TestBinder) service;
            testSevice = testBinder.getTestService();
            testSevice.setCallBack(callBack);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            testBinder = null;
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isServiceWork(getActivity(), TestSevice.class.getName())) {
            if (is_bindService) getActivity().unbindService(serviceConnection);
            getActivity().stopService(intent);
            testSevice.unCallBack();
            testSevice = null;
        }
        callBack = null;
        binding = null;
    }
}
