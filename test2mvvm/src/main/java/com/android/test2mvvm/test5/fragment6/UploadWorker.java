package com.android.test2mvvm.test5.fragment6;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.icu.util.Output;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.android.test2mvvm.R;
import com.android.test2mvvm.test4.Test4_Activity;
import com.android.test2mvvm.test5.Test5_Activity;
import com.android.test2mvvm.util.Loge;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

public class UploadWorker extends Worker {
    public UploadWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @Override
    public Result doWork() {
        // Do the work here--in this case, upload the images.

//        NotificationChannel channel = new NotificationChannel("channel", "study", NotificationManager.IMPORTANCE_HIGH);
//        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.createNotificationChannel(channel);

//        Intent intent = new Intent(getApplicationContext(), Test4_Activity.class);
//     //   intent.setPackage("com.example.jetpacktest");
//        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, PendingIntent.FLAG_IMMUTABLE);
//        Icon icon = Icon.createWithResource(getApplicationContext(), R.drawable.ic_loading);
//        Notification.Action action = new Notification.Action.Builder(icon, "学习", pendingIntent)
//                .build();
//        Notification.Builder notification = new Notification.Builder(getApplicationContext(), "1").setSmallIcon(icon)
//                .addAction(action);
//        manager.notify(1, notification.build());
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),"channel").setContentTitle("This is content title")
//                .setContentText("This is content text")
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.ic_launcher_background));
//        manager.notify(1, builder.build());
        // 创建通知的意图
        Intent intent = new Intent(getApplicationContext(), Test5_Activity.class);
        Bundle bundle=new Bundle();
        bundle.putString("key","取消worker");
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // 清除任务栈
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // 使用 NotificationCompat.Builder 类创建通知
        String channelId = "my_channel_id";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("天气订阅系统")
                .setContentText("欢迎订阅天气，伴你出行!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // 创建通知渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My Channel";
            Log.d("TAG","频道频道");
            String description = "This is my notification channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getApplicationContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        // 推送通知到通知栏
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        int notificationId = 2;
        notificationManagerCompat.notify(notificationId, builder.build());

        Loge.e("开始通知");

        return Result.success();
    }
}
