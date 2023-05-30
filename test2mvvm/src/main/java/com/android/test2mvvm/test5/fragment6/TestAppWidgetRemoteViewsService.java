package com.android.test2mvvm.test5.fragment6;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;

import com.android.test2mvvm.R;
import com.android.test2mvvm.util.Loge;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TestAppWidgetRemoteViewsService extends Service {

    Timer timer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Loge.e("服务创建");
        timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                updateTime();
            }
        };
        //服务启动时会每个1s去更新一次RemoteViews界面
        timer.schedule(timerTask, 0, 1000);

    }

    private void updateTime() {

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_widget);
        //将RemoteViews界面的TextView内容设置为时间，实现类似钟表的效果
        String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Loge.e(s);
        remoteViews.setTextViewText(R.id.test_app_widget_custom_tv, s);

        AppWidgetManager manager = AppWidgetManager.getInstance(getApplicationContext());

        ComponentName componentName = new ComponentName(this, TestAppWidgetRemoteViews.class);

        manager.updateAppWidget(componentName, remoteViews);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
