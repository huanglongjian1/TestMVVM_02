package com.android.test2mvvm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.test2mvvm.test4.Test;
import com.android.test2mvvm.test4.fragment3.RecyclerViewActivity;
import com.android.test2mvvm.util.Constants;
import com.android.test2mvvm.util.Loge;
import com.android.test2mvvm.util.SharedPreferencesUtil;
import com.tencent.mmkv.MMKV;

import java.util.List;

@Route(path = Constants.TEST2_MVVMACTIVITY)
public class Test2MVVMActivity extends AppCompatActivity {
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Test2_App.string = "初始化";
        setContentView(R.layout.activity_test2);
        ActionBar bar = getSupportActionBar();
        bar.hide();
        Loge.e(String.valueOf(android.os.Process.myPid()) + ":test2");
        findViewById(R.id.main_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constants.TEST6_ACTIVITY).navigation();
                // startActivity(new Intent(Test2MVVMActivity.this, Test.class));
                //  startActivity(new Intent(Test2MVVMActivity.this, RecyclerViewActivity.class));
                //    startActivity(new Intent(Test2MVVMActivity.this, Test.class));
            }
        });
        //    getCurrentProcessNameByActivityManager(this);
        findViewById(R.id.main_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e("保存成功");
                i++;
                SharedPreferencesUtil.setStringValue(Test2MVVMActivity.this, "key", "value" + i);
            }
        });
        findViewById(R.id.main_tv_03).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e("mmkv保存数据");
                MMKV mmkv = MMKV.defaultMMKV();
                mmkv.putString("mmkv", "今晚必定有MMKV" + i);
//                String value = SharedPreferencesUtil.getStringValue(Test2MVVMActivity.this, "key", "没有取到");
//                Loge.e(value);
                String value = mmkv.getString("key", "没有取到数据");
                Loge.e(value);
            }
        });

        //获取MMKV的实例对象
        MMKV preferences = MMKV.defaultMMKV();

//迁移旧数据
        SharedPreferences old_man = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.importFromSharedPreferences(old_man);
        old_man.edit().clear().commit();

    }

    public static String getCurrentProcessNameByActivityManager(@NonNull Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am != null) {
            List<ActivityManager.RunningAppProcessInfo> runningAppList = am.getRunningAppProcesses();
            if (runningAppList != null) {
                for (ActivityManager.RunningAppProcessInfo processInfo : runningAppList) {
                    if (processInfo.pid == pid) {
                        Loge.e(processInfo.processName + ":test2");
                        return processInfo.processName;
                    }
                }
            }
        }
        return null;
    }

}