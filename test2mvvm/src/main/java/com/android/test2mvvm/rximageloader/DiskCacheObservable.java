package com.android.test2mvvm.rximageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


import com.android.test2mvvm.rximageloader.retrofit.RetrofitManager;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.rxjava3.functions.Consumer;
import okhttp3.ResponseBody;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.io.FileSystem;
import okio.Buffer;
import okio.Sink;
import okio.Source;


public class DiskCacheObservable extends CacheObservable {

    private Context mContext;
    private DiskLruCache mDiskLruCache;
    private final int maxSize = 10 * 1024 * 1024;

    public DiskCacheObservable(Context mContext) {
        this.mContext = mContext;
        initDiskLruCache();
    }

    @Override
    public ImageBean getDataFromCache(String url) {

        Bitmap bitmap = getDataFromDiskLruCache(url);
        Log.e("getDataFromCache", "bitmap.toString()");
        return new ImageBean(bitmap, url);
    }

    @Override
    public void putDataToCache(ImageBean imageBean) {
        RetrofitManager.getInstance().creatserver().getimage(imageBean.getUrl()).doOnNext(new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody responseBody) throws Throwable {

                String key = DiskCacheUtils.getMD5String(imageBean.getUrl());
                DiskLruCache.Editor editor = null;
                try {
                    editor = mDiskLruCache.edit(key);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Sink sink = editor.newSink(0);
                Log.e("sink", "sink");

                InputStream inputStream = null;
                try {
                    //long fileSize = body.contentLength();
                    //long fileSizeDownloaded = 0;
                    byte[] fileReader = new byte[4096];
                    Buffer buffer = new Buffer();
                    inputStream = responseBody.byteStream();

                    while (true) {
                        int read = inputStream.read(fileReader);
                        if (read == -1) {
                            break;
                        }
                        buffer.write(fileReader, 0, read);
                        //fileSizeDownloaded += read;
                        sink.write(buffer, read);
                        buffer.clear();
                    }
                    if (buffer != null) {
                        buffer.clear();
                        buffer.close();
                    }

                    if (inputStream != null) {
                        inputStream.close();
                    }

                    if (sink != null) {
                        sink.flush();
                        sink.close();
                    }
                    editor.commit();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).subscribe();
    }


    public void initDiskLruCache() {
        File cacheDir = DiskCacheUtils.getDiskCacheDir(mContext, "our22_cache");
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
            Log.e("cach---eDir", cacheDir.getPath());
        }
        int versionCode = DiskCacheUtils.getAppVersion(mContext);
        //这里需要注意参数二：缓存版本号，只要不同版本号，缓存都会被清除，重新使用新的
        //     mDiskLruCache = DiskLruCache.creat(FileSystem.SYSTEM, cacheDir, versionCode, 1, maxSize);


    }

    /**
     * 获取文件缓存
     *
     * @param url
     * @return
     */
    private Bitmap getDataFromDiskLruCache(String url) {
        Bitmap bitmap = null;
        try {
            final String key = DiskCacheUtils.getMD5String(url);
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
            if (snapshot == null) return null;
            //获取资源的输出流,Source类似InputStream
            Source source = snapshot.getSource(0);
            Buffer buffer = new Buffer();
            //读取4*1024数据放入buffer中并返回读取到的数据的字节长度
            long ret = source.read(buffer, 4 * 1024);
            //判断文件是否读完
            while (ret != -1) {
                ret = source.read(buffer, 4 * 1024);
            }
            //获取到buffer的inputStream对象
            InputStream inputStream = buffer.inputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            if (inputStream != null) {
                inputStream.close();
            }
            if (buffer != null) {
                buffer.clear();
                buffer.close();
            }
            if (source != null) {
                source.close();
            }
            if (snapshot != null) {
                snapshot.close();
            }

            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return bitmap;
    }


}
