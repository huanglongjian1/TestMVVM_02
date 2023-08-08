package com.android.test2mvvm.test6.test_fragment3.senven;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.DownloadBinding;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.test6.test_fragment3.six.downbean.DownLoad_Bean;

public class Senven_Fragment extends Util_BaseFullBottomSheetFragment<DownloadBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.download;
    }

    DownloadManager downloadManager;
    DownloadManager.Request request;
    long downloadID;

    @Override
    protected void initView() {
        binding.setBean(new DownLoad_Bean());

        binding.test6FragmentDown.setText("下载图片");
        binding.test6FragmentDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadID = downloadManager.enqueue(request);

            }
        });
        binding.test6FragmentProTv.setText("显示");
        binding.test6FragmentProTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryID(downloadID);
            }
        });
        binding.test6FragmentCancel.setText("取消");
        binding.test6FragmentCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadManager.remove(downloadID);
            }
        });
    }

    @Override
    protected void initData() {
        downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        request = new DownloadManager.Request(Uri.parse("https://img-blog.csdn.net/20161012161744476"));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setTitle("下载图片...");

        downloadReceiver=new DownloadReceiver();
        //注册广播，监听下载状态
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        intentfilter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        getActivity().registerReceiver(downloadReceiver, intentfilter);
    }

    private void queryID(long id) {
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(id);
        Cursor cursor = downloadManager.query(query);

        if (cursor != null) {

            while (cursor.moveToNext()) {

                String bytesDownload = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                String descrition = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_DESCRIPTION));
                String idc = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_ID));
                String localUri = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                String mimeType = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_MEDIA_TYPE));
                String title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE));
                String status = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                String totalSize = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));

                binding.test6FragmentProTv.setText(bytesDownload + "\n" +
                        descrition + "\n" +
                        idc + "\n" +
                        localUri + "\n" +
                        mimeType + "\n" +
                        title + "\n" +
                        status + "\n" +
                        totalSize + "\n");
                Log.i("MainActivity", "bytesDownload:" + bytesDownload);
                Log.i("MainActivity", "descrition:" + descrition);
                Log.i("MainActivity", "id:" + idc);
                Log.i("MainActivity", "localUri:" + localUri);
                Log.i("MainActivity", "mimeType:" + mimeType);
                Log.i("MainActivity", "title:" + title);
                Log.i("MainActivity", "status:" + status);
                Log.i("MainActivity", "totalSize:" + totalSize);

            }

        }

    }

    @Override
    protected void onDataLazyLoad() {

    }

    DownloadReceiver downloadReceiver;

    /**
     * 广播接收器，接受ACTION_DOWNLOAD_COMPLETE和ACTION_NOTICATION_CLICKED
     */
    class DownloadReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {

                Uri uri = downloadManager.getUriForDownloadedFile(downloadID);

                binding.test6FragmentImage.setImageURI(uri);

            } else if (intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)) {

                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();

            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(downloadReceiver);
    }
}
