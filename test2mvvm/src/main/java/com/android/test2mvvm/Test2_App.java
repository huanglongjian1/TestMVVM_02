package com.android.test2mvvm;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.startup.AppInitializer;

import com.alibaba.android.arouter.launcher.ARouter;
import com.android.test2mvvm.test1.fragment10.binding.ProductionComponent;
import com.android.test2mvvm.test5.fragment6.cp.TestProvider;
import com.android.test2mvvm.test5.fragment6.startup.SDKInitializer;
import com.android.test2mvvm.test5.fragment7.dao.AppDatabase;

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

    @Override
    public void onCreate() {
        super.onCreate();
        db=AppDatabase.getInstance(this);
        context = this;
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);

        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效

            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
        DataBindingUtil.setDefaultComponent(new ProductionComponent());

    }
    public static AppDatabase getDb(){
        return db;
    }
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
