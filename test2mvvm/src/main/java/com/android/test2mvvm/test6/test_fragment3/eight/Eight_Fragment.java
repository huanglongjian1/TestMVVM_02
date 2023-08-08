package com.android.test2mvvm.test6.test_fragment3.eight;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.ActivityDownloadBinding;
import com.android.test2mvvm.test6.fragments.result.fragmenta.StringAdapter;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class Eight_Fragment extends Util_BaseFullBottomSheetFragment<ActivityDownloadBinding> implements View.OnClickListener {
    private TextView down;
    private TextView progress;
    private TextView file_name;
    private ProgressBar pb_update;
    private DownloadManager downloadManager;
    private DownloadManager.Request request;
    public static String downloadUrl = "http://ucdl.25pp.com/fs08/2017/01/20/2/2_87a290b5f041a8b512f0bc51595f839a.apk";
    Timer timer;
    long id;
    TimerTask task;
    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int pro = bundle.getInt("pro");
            String name = bundle.getString("name");
            pb_update.setProgress(pro);
            progress.setText(String.valueOf(pro) + "%");
            file_name.setText(name);
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        down = binding.down;
        progress = binding.progress;
        file_name = binding.fileName;
        pb_update = binding.pbUpdate;
        down.setOnClickListener(this);
        downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        request = new DownloadManager.Request(Uri.parse(downloadUrl));

        request.setTitle("大象投教");
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        request.setMimeType("application/vnd.android.package-archive");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //创建目录
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).mkdir();

        //设置文件存放路径
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "app-release.apk");
        pb_update.setMax(100);
        final DownloadManager.Query query = new DownloadManager.Query();
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                Cursor cursor = downloadManager.query(query.setFilterById(id));
                if (cursor != null && cursor.moveToFirst()) {
                    if (cursor.getInt(
                            cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                        pb_update.setProgress(100);
                        install(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/app-release.apk");
                        task.cancel();

//                        Uri uri = downloadManager.getUriForDownloadedFile(id);
//                        Loge.e(uri.toString() + "=================");
//                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                        intent.setDataAndType(uri, "application/vnd.android.package-archive");
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                        startActivity(intent);
                    }
                    String title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE));
                    String address = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    int bytes_downloaded = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                    int bytes_total = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                    int pro = (bytes_downloaded * 100) / bytes_total;
                    Message msg = Message.obtain();
                    Bundle bundle = new Bundle();
                    bundle.putInt("pro", pro);
                    bundle.putString("name", title);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
                cursor.close();
            }
        };
        timer.schedule(task, 0, 1000);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_download;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDataLazyLoad() {

    }

    private void install(String path) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //  intent.setDataAndType(Uri.parse("file://" + path), "application/vnd.android.package-archive");
        //   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//4.0以上系统弹出安装成功打开界面
        //startActivity(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".fileprovider2", new File(path));
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");

            Loge.e(contentUri.toString() + "=========1");
        } else {
            intent.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }


        requireActivity().getActivityResultRegistry().register("install", new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

            }
        }).launch(intent);
    }

    @Override
    public void onClick(View v) {
        id = downloadManager.enqueue(request);
        task.run();
        down.setClickable(false);
        down.setBackgroundResource(R.drawable.ic_launcher_background);
    }
}
