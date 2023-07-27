package com.android.test2mvvm.test6.test_fragment.test08;

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
import androidx.databinding.DataBindingUtil;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.basebottomsheet.BaseFullBottomSheetFragment;
import com.android.test2mvvm.test6.test_fragment.CallBack;
import com.android.test2mvvm.test6.test_fragment.test07.TestService_03;
import com.android.test2mvvm.util.Loge;

import java.util.List;

public class Fragment_08 extends BaseFullBottomSheetFragment {
    TestBottomsheetfragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.test_bottomsheetfragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Intent intent = new Intent(getActivity(), TestService_03.class);
        getActivity().bindService(intent, serviceConnection, Activity.BIND_AUTO_CREATE);
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isServiceWork(getActivity(), TestService_03.class.getName()))
                    testService_03.start_task(100);
            }
        });
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().bindService(intent, serviceConnection1, Activity.BIND_AUTO_CREATE);
            }
        });
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isServiceWork(getActivity(), TestService_03.class.getName())) {
                    Intent intent1 = new Intent(getActivity(), TestService_03.class);
                    getActivity().startService(intent1);
                }
            }
        });
        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isServiceWork(getActivity(), TestService_03.class.getName())) {
                    if (connect) getActivity().unbindService(serviceConnection);
                    if (connect1) getActivity().unbindService(serviceConnection1);
                    getActivity().stopService(new Intent(getActivity(), TestService_03.class));
                }


                if (isServiceWork(getActivity(), TestService_03.class.getName())) {
                    Loge.e("服务还在");
                } else {
                    Loge.e("没有找到服务");
                }
            }
        });
    }

    CallBack callBack = new CallBack() {
        @Override
        public void onResult(String result) {
            Loge.e(result);
        }
    };
    TestService_03 testService_03;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            connect = true;
            TestService_03.Test03Binder binder = (TestService_03.Test03Binder) service;
            testService_03 = binder.getService();
            Loge.e(binder.toString());
            testService_03.setCallBack(callBack);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            connect = false;
        }
    };

    ServiceConnection serviceConnection1 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            connect1 = true;
            TestService_03.Test03Binder binder = (TestService_03.Test03Binder) service;
            Loge.e(binder.toString());
            Loge.e(testService_03.toString());
            Loge.e(binder.getService().toString());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            connect1 = false;
        }
    };
    boolean connect = false;
    boolean connect1 = false;

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isServiceWork(getActivity(), TestService_03.class.getName())) {
            testService_03.unCallBack();
            getActivity().stopService(new Intent(getActivity(), TestService_03.class));
            if (connect) getActivity().unbindService(serviceConnection);
            if (connect1) getActivity().unbindService(serviceConnection1);
        }
        serviceConnection = null;
        serviceConnection1 = null;
        binding = null;
        testService_03 = null;

        Loge.e("Fragment08_onDestroy");
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
