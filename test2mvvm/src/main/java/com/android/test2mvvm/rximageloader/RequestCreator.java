package com.android.test2mvvm.rximageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import okhttp3.ResponseBody;

public class RequestCreator {
    public MemoryCacheObservable memoryCacheObservable;
    public DiskCacheObservable diskCacheObservable;


    public RequestCreator(Context context) {
        memoryCacheObservable = new MemoryCacheObservable();
        diskCacheObservable = new DiskCacheObservable(context);

    }

    public Observable<ImageBean> getImageFromMemory(String url) {
        return memoryCacheObservable.getImage(url)
                .filter(new Predicate<ImageBean>() {
                    @Override
                    public boolean test(@NonNull ImageBean imageBean) throws Exception {
                        Bitmap bitmap = imageBean.getBitmap();
                        Log.e("getImageFromMemory", "null");
                        return bitmap != null;
                    }
                });

    }

    public Observable<ImageBean> getImageFromDisk(String url) {
        return diskCacheObservable.getImage(url)
                .filter(new Predicate<ImageBean>() {
                    @Override
                    public boolean test(@NonNull ImageBean imageBean) throws Exception {
                        Bitmap bitmap = imageBean.getBitmap();
                        if (bitmap != null) {
                            return true;
                        } else {
                            diskCacheObservable.putDataToCache(imageBean);
                            ImageBean imageBean1 = diskCacheObservable.getDataFromCache(imageBean.getUrl());
                            if (imageBean1.getBitmap() != null) {
                                Log.e("diskCacheObservable.putDataToCache", "true");
                                return true;
                            }
                        }
                        return true;
                    }
                }).doOnNext(new Consumer<ImageBean>() {
                    @Override
                    public void accept(@NonNull ImageBean imageBean) throws Exception {
                        //缓存内存
                        Log.e("memoryCacheObservable.putDataToCache", "缓存到内存");
                        memoryCacheObservable.putDataToCache(imageBean);
                    }
                });

    }

}