package com.android.test2mvvm.test5.fragment6.cp;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Observer;
import androidx.work.BackoffPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test5Fragment5Binding;
import com.android.test2mvvm.test4.Test4_Activity;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test5.Test5_Activity;
import com.android.test2mvvm.test5.fragment6.TestAppWidgetRemoteViewsService;
import com.android.test2mvvm.test5.fragment6.UploadWorker;
import com.android.test2mvvm.test5.fragment6.cp.room.CP_AppDatabase;
import com.android.test2mvvm.test5.fragment6.cp.room.CP_Bean;
import com.android.test2mvvm.test5.fragment6.cp.room.CP_Dao;
import com.android.test2mvvm.util.Loge;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

public class Fragment6_room extends BaseFragment<Test5Fragment5Binding> {
    @Override
    protected int getLayoutId() {
        Intent intent = new Intent(getContext(), TestAppWidgetRemoteViewsService.class);
        getContext().startService(intent);
        return R.layout.test5_fragment5;
    }

    @Override
    protected void initView() {
        WorkRequest myWorkRequest =
                new OneTimeWorkRequest.Builder(UploadWorker.class)
                        .setBackoffCriteria(
                                BackoffPolicy.LINEAR,
                                OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                                TimeUnit.MILLISECONDS)
                        .build();
        WorkManager.getInstance(getContext()).enqueue(myWorkRequest);

        binding.test5Fragment5Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Observable.interval(100, TimeUnit.MILLISECONDS)
                        .take(101)
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Throwable {
                                Loge.e("complete");
                                NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                                notificationManager.cancel(notificatonID);
                            }
                        })
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Throwable {

                                int progress = Math.toIntExact(aLong);
                                Loge.e(progress + "--");
                                testRemoteViewsInNotification(progress);
                            }
                        });

                Loge.e("通知");
            }
        });
    }

    int notificatonID = 100086;

    @TargetApi(26)
    public void testRemoteViewsInNotification(int progress) {
        Intent intent = new Intent(getContext(), Test4_Activity.class);
        Bundle bundle = new Bundle();
        bundle.putString("key", "今晚打老虎");
        intent.putExtras(bundle);
        PendingIntent pendingIntent = PendingIntent
                .getActivity(getContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);

        String id = "my_channel_01";
        CharSequence name = "channel";
        String description = "description";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(id, name, importance);
        channel.setDescription(description);
        channel.enableLights(true);
        channel.setLightColor(Color.RED);
        channel.enableVibration(true);
        // 偶数表示静止时间，奇数表示振动时间
        channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

        NotificationManager manager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);

        RemoteViews remoteViews = new RemoteViews(getActivity().getPackageName(), R.layout.fragment_home);
        remoteViews.setTextViewText(R.id.text_home, "剩余" + (99 - progress) + "%");
        remoteViews.setOnClickPendingIntent(R.id.text_home, pendingIntent);
        remoteViews.setProgressBar(R.id.pb_kugousong, 100, progress, false);

        Notification notificaton = new Notification.Builder(getActivity(), id)
                .setAutoCancel(false)
                .setContentTitle("title")
                .setContentText("text")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setOngoing(true)
                .setCustomContentView(remoteViews)
                .setWhen(System.currentTimeMillis())
                .build();
        manager.notify(notificatonID, notificaton);

    }

    @Override
    protected void initData() {
        Uri uri = Uri.parse("content://test2mvvm.testprovider/user");
        ContentResolver contentResolver = getContext().getContentResolver();
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            Loge.e(cursor.getString(0) + "-----" + cursor.getString(1));
        }
    }
}
