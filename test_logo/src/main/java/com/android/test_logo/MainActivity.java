package com.android.test_logo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效

            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(getApplication()); // 尽可能早，推荐在Application中初始化

        findViewById(R.id.test_logo_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    ARouter.getInstance().build("/test2mvvm/Test2MVVMActivity").navigation();
                Intent intent=new Intent();
                intent.setAction("aaa.bbb.ccc.ddd");
                intent.addCategory("android.intent.category.DEFAULT");
                startActivity(intent);
            }
        });
    }

    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}