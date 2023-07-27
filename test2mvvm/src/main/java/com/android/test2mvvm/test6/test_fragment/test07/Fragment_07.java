package com.android.test2mvvm.test6.test_fragment.test07;

import android.app.Activity;
import android.content.ComponentName;
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

public class Fragment_07 extends BaseFullBottomSheetFragment {
    TestBottomsheetfragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = TestBottomsheetfragmentBinding.inflate(inflater);

        Intent intent=new Intent(getActivity(),TestService_03.class);
        getActivity().bindService(intent,serviceConnection,Activity.BIND_AUTO_CREATE);


        return binding.getRoot();
    }

    private ServiceConnection connection;
    private TestService<String> testService;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                TestService.MyBinder myBinder = (TestService.MyBinder) service;
                testService = myBinder.getTestService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                if (testService == null || !testService.isCreate) {
                    getActivity().bindService(new Intent(getActivity(), TestService.class), connection, Activity.BIND_AUTO_CREATE);
                    Loge.e("正在启动服务,再次点击可获取数据");
                }
            }
        };
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (testService == null || !testService.isCreate) {
                    getActivity().bindService(new Intent(getActivity(), TestService.class), connection, Activity.BIND_AUTO_CREATE);
                    Loge.e("正在启动服务,再次点击可获取数据");
                } else {
                    Loge.e(String.valueOf(testService.getNum()));
                }
            }
        });
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (testService == null || !testService.isCreate) return;
                testService.setData("我是一个兵");

            }
        });
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TestService_02.class);
                intent.putExtra("service_key", System.currentTimeMillis());
                getActivity().startService(intent);
            }
        });


         binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testService_03.start_task(10);
            }
        });
    }
    TestService_03 testService_03;
    ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
           Loge.e("连接上");
            TestService_03.Test03Binder binder= (TestService_03.Test03Binder) service;
            testService_03=binder.getService();
            testService_03.setCallBack(new CallBack() {
                @Override
                public void onResult(String result) {
                    Loge.e(result);
                }
            });

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    public void onPause() {
        super.onPause();
       if (testService!=null){
           if (testService.isCreate){
               getActivity().unbindService(connection);
           }
       }
        if (connection != null) connection = null;
        if (testService != null) testService = null;
        getActivity().stopService(new Intent(getActivity(),TestService_02.class));
    }
}
