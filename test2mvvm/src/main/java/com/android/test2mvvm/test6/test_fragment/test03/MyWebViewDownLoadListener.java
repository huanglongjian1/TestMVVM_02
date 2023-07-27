package com.android.test2mvvm.test6.test_fragment.test03;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.webkit.DownloadListener;
import android.widget.Toast;

import com.android.test2mvvm.retrofit2.api.RetrofitUtils;
import com.android.test2mvvm.util.Loge;

import java.io.File;
import java.net.URLDecoder;

//内部类
public class MyWebViewDownLoadListener implements DownloadListener {
    public MyWebViewDownLoadListener(Context context) {
        mContext = context;
    }

    Context mContext;

    @Override
    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                long contentLength) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Loge.e("需要SD卡");
            return;
        }else {
            Loge.e("SD卡已开，请直接下载:"+url);
        }
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        mContext.startActivity(intent);
    }
}