package com.android.test2mvvm.test6.test_fragment.test11;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.android.test2mvvm.R;
import com.android.test2mvvm.Test2_App;
import com.android.test2mvvm.databinding.TestBottomsheetfragmentBinding;
import com.android.test2mvvm.test6.basebottomsheet.BaseFullBottomSheetFragment;
import com.android.test2mvvm.test6.test_fragment.util.MyDialog;
import com.android.test2mvvm.util.Loge;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Fragment_11 extends BaseFullBottomSheetFragment {
    TestBottomsheetfragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.test_bottomsheetfragment, container, false);
        return binding.getRoot();
    }

    DialogReceiver dialogReceiver;

    @Override
    public void onResume() {
        super.onResume();
        Context context = getActivity();
        context1 = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Intent intent = new Intent(getContext(), MsgService.class);
        //      EventBus.getDefault().register(getActivity());

        getActivity().startService(intent);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("MsgReceiver");
        getActivity().registerReceiver(msgReceiver, intentFilter);

        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction("Test2");
        getActivity().registerReceiver(new Test2Receiver(), intentFilter1);

        dialogReceiver = new DialogReceiver();
        IntentFilter dialogIntentFilter = new IntentFilter();
        dialogIntentFilter.addAction("dialog");
        getActivity().registerReceiver(dialogReceiver, dialogIntentFilter);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(dialogReceiver, dialogIntentFilter);
        binding.test6FragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent("MsgReceiver");
                getActivity().sendBroadcast(intent1);
            }
        });
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent("MsgReceiver");
                intent1.setPackage(getActivity().getPackageName());
                getActivity().sendBroadcast(intent1);
            }
        });
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent("OffLine");
                getActivity().sendBroadcast(intent1);
            }
        });
        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent("Test2");
                getActivity().sendBroadcast(intent1);
            }
        });
        binding.test6FragmentBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent("dialog");
                //  LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent1);
                getActivity().sendBroadcast(intent1);
            }
        });
        binding.test6FragmentBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent("com.test2static");
                intent1.setPackage(getActivity().getPackageName());
                getActivity().sendBroadcast(intent1);
            }
        });
        binding.test6FragmentBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MessageService.class);
                getActivity().startService(intent);
                Loge.e("开启服务");

            }
        });

    }

    MsgReceiver msgReceiver = new MsgReceiver();

    public class MsgReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Loge.e(String.valueOf(intent.getIntExtra("key", 0)));
        }
    }

    public static class TestReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Loge.e(intent.getAction() + ":收到的广播");
        }
    }

    public class Test2Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Loge.e(intent.getAction() + ":广播");

        }
    }

    public class DialogReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Loge.e("DialogReceiver" + context.toString());
            MyDialog myDialog = new MyDialog();
            myDialog.show(getChildFragmentManager(), intent.getAction());
        }
    }

    public static class TestStaticReceiver extends BroadcastReceiver {
        @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
        public void onMsg(String msg) {
            Loge.e(msg);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Loge.e(context.toString() + "--------------");
            EventBus.getDefault().register(Test2_App.getInstance());
            EventBus.getDefault().postSticky("测试发信息");
            EventBus.getDefault().unregister(Test2_App.getInstance());


        }
    }

    public static Context context1;


    @Override
    public void onDestroy() {
        super.onDestroy();
        //    EventBus.getDefault().unregister(getActivity());
        Intent intent = new Intent(getContext(), MsgService.class);
        getActivity().stopService(intent);
        getActivity().unregisterReceiver(msgReceiver);
        getActivity().unregisterReceiver(dialogReceiver);
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(dialogReceiver);
    }
}
