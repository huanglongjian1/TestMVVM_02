package com.android.test2mvvm;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.launcher.ARouter;
import com.android.test2mvvm.test1.fragment10.binding.ProductionComponent;
import com.android.test2mvvm.test5.fragment7.dao.AppDatabase;
import com.android.test2mvvm.util.Loge;
import com.tencent.mmkv.MMKV;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class Test2_App extends Application {
    public static boolean isTest = true;
    public static Application context;
    //数据库
    public static AppDatabase db;

    public static Application getInstance() {
        return context;
    }

    public static String string = "null";

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化MMKV组件
        String rootDir = MMKV.initialize(this);
        //打印MMKV文件的存放根目录（可以不写）
        Loge.e("mmkv root: " + rootDir);

        db = AppDatabase.getInstance(this);
        context = this;
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);

        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效

            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
        DataBindingUtil.setDefaultComponent(new ProductionComponent());
        //   getCurrentProcessNameByActivityManager(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Loge.e(Application.getProcessName() + "     :app");
        }

    }

    public static AppDatabase getDb() {
        return db;
    }

    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    public static String getCurrentProcessNameByActivityManager(@NonNull Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am != null) {
            List<ActivityManager.RunningAppProcessInfo> runningAppList = am.getRunningAppProcesses();
            if (runningAppList != null) {
                for (ActivityManager.RunningAppProcessInfo processInfo : runningAppList) {
                    if (processInfo.pid == pid) {
                        Loge.e(processInfo.processName + ":app");
                        return processInfo.processName;
                    }
                }
            }
        }
        return null;
    }

    public void test(String s) {
        Loge.e("测试一下" + s);
    }

    public void test() {
        Loge.e("测试一下");
    }

    public void test(String s, Integer jj) {
        Loge.e("测试一下" + s + "-" + jj);
    }

    public void test(Boolean b, Integer integer) {
        Loge.e(String.valueOf(b) + integer + "测试测试");
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void Msg(String msg){
        Loge.e(msg);
    }
}
