package com.android.test2mvvm.test6.test_fragment3.six;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.android.test2mvvm.test6.test_fragment3.six.downbean.DownLoad_Bean;

import java.util.Timer;
import java.util.TimerTask;

public class DownLoad_Util {
    private DownloadManager downloadManager;
    private DownloadManager.Query query;
    private Timer mTimer;
    //下载任务的id
    private long mDownloadId;
    private Activity mContext;
    private DownLoad_Bean downLoad_bean;

    public DownLoad_Bean getDownLoad_bean() {
        return downLoad_bean;
    }

    public DownLoad_Util(Activity mContext) {
        this.mContext = mContext;
        downLoad_bean = new DownLoad_Bean();
        mTimer = new Timer();
        handler = new Handler(mContext.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                int pro = bundle.getInt("progressMsg");
                //    pb_update.setProgress(pro);
                //  progressTxt.setText("下载进度：" + pro + "%");
                downLoad_bean.setProgress(pro);
            }
        };
    }

    public long downloadAPK(String downloadurl, DownloadManager downloadManager) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadurl));

        //设置Notifcaiton的标题和描述
        request.setTitle("QQ.apk");
        request.setDescription("最新1.5.1版本下载中.....");

        //指定在WIFI状态下，执行下载操作。
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        //指定在MOBILE状态下，执行下载操作
        //request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE);
        //移动网络情况下是否允许漫游
        request.setAllowedOverRoaming(false);
        //下载情况是否显示在systemUI下拉状态栏中
        request.setVisibleInDownloadsUi(true);

        //设置Notification的显示，和隐藏
        /*
        在下载进行中时显示，在下载完成后就不显示了。可以设置如下三个值：
        VISIBILITY_HIDDEN 下载UI不会显示，也不会显示在通知中，如果设置该值，需要声明android.permission.DOWNLOAD_WITHOUT_NOTIFICATION
        VISIBILITY_VISIBLE 当处于下载中状态时，可以在通知栏中显示；当下载完成后，通知栏中不显示
        VISIBILITY_VISIBLE_NOTIFY_COMPLETED 当处于下载中状态和下载完成时状态，均在通知栏中显示
        VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION 只在下载完成时显示在通知栏中。
        * */
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        //设置为可被媒体扫描器找到
        request.allowScanningByMediaScanner();

        //设置下载路径 sdcard/download/
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "QQ.apk");

        //加入到下载的队列中。一旦下载管理器准备好执行并且连接可用，下载将自动启动。
        //一个下载任务对应唯一个ID， 此id可以用来去查询下载内容的相关信息
        long downloadID = downloadManager.enqueue(request);
        downLoad_bean.setId(downloadID);
        mDownloadId = downloadID;
        downLoad_bean.setUrl(downloadurl);
        query = new DownloadManager.Query();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                Cursor cursor = downloadManager.query(query.setFilterById(mDownloadId));

                if (cursor != null && cursor.moveToFirst()) {
                    int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                    Log.d("down", "status:" + status);
                    switch (status) {
                        //下载暂停
                        case DownloadManager.STATUS_PAUSED:
                            mContext.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    downLoad_bean.setDownload("下载暂停");
                                }
                            });
                            break;
                        //下载延迟
                        case DownloadManager.STATUS_PENDING:
                            Log.e("down", "====下载延迟=====");
                            mContext.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    downLoad_bean.setDownload("====下载延迟=====");
                                }
                            });
                            break;
                        //正在下载
                        case DownloadManager.STATUS_RUNNING:
                            //Log.e("down", "====正在下载中=====");
                            mContext.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    downLoad_bean.setDownload("正在下载中......");
                                }
                            });
                            break;
                        //下载完成
                        case DownloadManager.STATUS_SUCCESSFUL:
                            //下载完成安装APK
                            //Log.e("down", "====下载完成=====");
                            mTimerTask.cancel();
                            mContext.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //  pb_update.setProgress(100);
                                    // download.setText("下载完成");
                                    downLoad_bean.setProgress(100);
                                    downLoad_bean.setDownload("下载完成");

                                    if (mTimer != null) {
                                        mTimer.cancel();
                                    }
                                    if (mTimerTask != null) {
                                        mTimerTask.cancel();
                                    }
                                    handler.removeCallbacksAndMessages(null);
                                    mContext.unregisterReceiver(receiver);


                                }
                            });
                            break;
                        //下载失败
                        case DownloadManager.STATUS_FAILED:
                            mContext.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(mContext, "下载失败", Toast.LENGTH_LONG).show();
                                }
                            });
                            break;
                    }


                    long bytesDownload = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                    String descrition = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_DESCRIPTION));
                    String id = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_ID));
                    String localUri = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    String mimeType = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_MEDIA_TYPE));
                    String title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE));
                    long totalSize = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));

                    Log.d("down", "bytesDownload:" + bytesDownload);
                    Log.d("down", "totalSize:" + totalSize);

                    int progress = (int) (bytesDownload * 100 / totalSize);
                    Message msg = Message.obtain();
                    Bundle bundle = new Bundle();
                    bundle.putInt("progressMsg", progress);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
                cursor.close();
            }
        };
        mTimer.schedule(mTimerTask, 0, 2000);
        //注册广播，监听下载状态
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        intentfilter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        mContext.registerReceiver(receiver, intentfilter);

        return downloadID;
    }

    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                Toast.makeText(mContext, "下载完成", Toast.LENGTH_SHORT).show();
                try {
                    //跳转到显示下载内容的activity界面
                    Intent dm = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
                    dm.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //    context.startActivity(dm);
                    AppCompatActivity activity = (AppCompatActivity) mContext;
                    activity.getActivityResultRegistry().register("download", new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {

                        }
                    }).launch(dm);

                } catch (ActivityNotFoundException ex) {
                    Log.e("down", "no activity for " + ex.getMessage());
                }
            } else if (intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)) {
                Toast.makeText(mContext, "用户点击了通知栏", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public Handler handler;

    private TimerTask mTimerTask;
}
