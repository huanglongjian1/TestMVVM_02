package com.android.test2mvvm.rximageloader;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

public abstract class CacheObservable {

    /**
     * 获取缓存数据
     * @param url
     * @return
     */
    public Observable<ImageBean> getImage(final String url) {
        return Observable.create(new ObservableOnSubscribe<ImageBean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<ImageBean> e) throws Exception {
                if (!e.isDisposed()) {
                    ImageBean image = getDataFromCache(url);
                    e.onNext(image);
                    e.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 取出缓存数据
     * @param url
     * @return
     */
    public abstract ImageBean getDataFromCache(String url);

    /**
     * 缓存数据
     * @param image
     */
    public abstract void putDataToCache(ImageBean image);
}