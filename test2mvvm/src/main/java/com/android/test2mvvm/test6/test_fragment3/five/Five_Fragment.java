package com.android.test2mvvm.test6.test_fragment3.five;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.DownloadBinding;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

import java.util.Timer;
import java.util.TimerTask;

public class Five_Fragment extends Util_BaseFullBottomSheetFragment<DownloadBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.download;
    }

    private DownloadManager downloadManager;

    private DownloadManager.Query query;

    private DownloadTest mDownloadTest;


    private Timer mTimer;

    //下载任务的id
    private long mDownloadId;
    private TextView download;
    private TextView progressTxt;
    private ProgressBar pb_update;
    private TextView mCancleBtn;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                Toast.makeText(getActivity(), "下载完成", Toast.LENGTH_SHORT).show();
                try {
                    //跳转到显示下载内容的activity界面
                    Intent dm = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
                    dm.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(dm);
                } catch (ActivityNotFoundException ex) {
                    Log.d("down", "no activity for " + ex.getMessage());
                }
            } else if (intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)) {
                Toast.makeText(getActivity(), "用户点击了通知栏", Toast.LENGTH_SHORT).show();
            }
        }
    };


    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int pro = bundle.getInt("progressMsg");
            pb_update.setProgress(pro);
            progressTxt.setText("下载进度：" + pro + "%");
        }
    };

    private TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            Cursor cursor = downloadManager.query(query.setFilterById(mDownloadId));

            if (cursor != null && cursor.moveToFirst()) {
                int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                Log.d("down", "status:" + status);
                switch (status) {
                    //下载暂停
                    case DownloadManager.STATUS_PAUSED:
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                download.setText("下载暂停");
                            }
                        });
                        break;
                    //下载延迟
                    case DownloadManager.STATUS_PENDING:
                        Log.e("down", "====下载延迟=====");
                        break;
                    //正在下载
                    case DownloadManager.STATUS_RUNNING:
                        //Log.e("down", "====正在下载中=====");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                download.setText("正在下载中......");
                            }
                        });
                        break;
                    //下载完成
                    case DownloadManager.STATUS_SUCCESSFUL:
                        //下载完成安装APK
                        //Log.e("down", "====下载完成=====");
                        mTimerTask.cancel();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pb_update.setProgress(100);
                                download.setText("下载完成");
                            }
                        });
                        break;
                    //下载失败
                    case DownloadManager.STATUS_FAILED:
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "下载失败", Toast.LENGTH_LONG).show();
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

    @Override
    protected void initView() {
        download = binding.test6FragmentDown;
        progressTxt = binding.test6FragmentProTv;
        pb_update = binding.test6Fragment5Progressbar;
        mCancleBtn = binding.test6FragmentCancel;


        downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        query = new DownloadManager.Query();
        mDownloadTest = new DownloadTest(getActivity());

        //定时器
        mTimer = new Timer();

        //注册广播，监听下载状态
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        intentfilter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        getActivity().registerReceiver(receiver, intentfilter);

    }

    @Override
    public void onResume() {
        super.onResume();
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDownloadId = mDownloadTest.downloadAPK("https://downv6.qq.com/qqweb/QQ_1/android_apk/Android_8.9.73_64.apk", downloadManager);
                mTimer.schedule(mTimerTask, 0, 1000);
                download.setEnabled(false);
            }
        });

        mCancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadManager.remove(mDownloadId);
                download.setText("立即下载");
            }
        });

        binding.test6FragmentProTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e(String.valueOf(downloadManager.getUriForDownloadedFile(mDownloadId)));
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
        }
        if (mTimerTask != null) {
            mTimerTask.cancel();
        }
        handler.removeCallbacksAndMessages(null);
        getActivity().unregisterReceiver(receiver);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDataLazyLoad() {

    }
}
