package com.android.test2mvvm.test5.fragment6;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

import com.android.test2mvvm.util.Loge;

public class TestAppWidgetRemoteViews extends AppWidgetProvider {

    /**
     * 当小组件被添加到屏幕上时回调
     */
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Loge.e("搬迁到屏幕");
        //启动TestAppWidgetRemoteViewsService服务
        context.startService(new Intent(context, TestAppWidgetRemoteViewsService.class));
    }

    /**
     * 当小组件被添加或每次被刷新时回调，更新时机由android:updatePeriodMillis来指定
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Loge.e("onUpdate");
    }
    /**
     * 当widget小组件从屏幕移除时回调
     */
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Loge.e("onDeleted");
    }

    /**
     * 当最后一个该类型的小组件被从屏幕中移除时回调
     */
    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Loge.e("onDisabled");

    }

}
