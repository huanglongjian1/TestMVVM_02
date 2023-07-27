package com.android.test2mvvm;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.app.Activity;
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
import com.android.test2mvvm.test6.Test6_Activity;
import com.android.test2mvvm.test6.test_fragment2.util.Util_ActivityResultContract;
import com.android.test2mvvm.util.Constants;
import com.android.test2mvvm.util.Loge;
import com.android.test2mvvm.util.SharedPreferencesUtil;
import com.tencent.mmkv.MMKV;

import java.util.List;

@Route(path = Constants.TEST2_MVVMACTIVITY)
public class Test2MVVMActivity extends AppCompatActivity {
    int i = 0;
    ActivityResultLauncher launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActivityResultContract activityResultContract = new ActivityResultContract() {
//            @NonNull
//            @Override
//            public Intent createIntent(@NonNull Context context, Object input) {
//                String name = (String) input;
//                try {
//                    Class<Activity> activityClass = (Class<Activity>) Class.forName(name);
//                    return new Intent(Test2MVVMActivity.this, activityClass);
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//
//            @Override
//            public Object parseResult(int resultCode, @Nullable Intent intent) {
//                if (resultCode == Activity.RESULT_OK) {
//                    String data = intent.getStringExtra("result");
//                    return data;
//                } else {
//                    return "";
//                }
//
//            }
//        };
        launcher = registerForActivityResult(Util_ActivityResultContract.startActivityForResult(), new ActivityResultCallback<String>() {
            @Override
            public void onActivityResult(String result) {
                Loge.e("返回数据:" + result);
            }
        });

        Test2_App.string = "初始化";
        setContentView(R.layout.activity_test2);
        ActionBar bar = getSupportActionBar();
        bar.hide();
        Loge.e(String.valueOf(android.os.Process.myPid()) + ":test2");
        findViewById(R.id.main_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   ARouter.getInstance().build(Constants.TEST6_ACTIVITY).navigation();
                // startActivity(new Intent(Test2MVVMActivity.this, Test.class));
                //  startActivity(new Intent(Test2MVVMActivity.this, RecyclerViewActivity.class));
                //    startActivity(new Intent(Test2MVVMActivity.this, Test.class));
             //   launcher.launch(Test6_Activity.class.getName());
                launcher.launch(new Util_ActivityResultContract.Input(Test6_Activity.class.getName(),1));
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