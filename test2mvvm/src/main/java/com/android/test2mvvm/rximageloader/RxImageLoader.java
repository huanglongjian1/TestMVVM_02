package com.android.test2mvvm.rximageloader;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxImageLoader {

    static RxImageLoader singleton;
    private String mUrl;
    private RequestCreator requestCreator;

    //防止用户可以创建该对象
    private RxImageLoader(Builder builder) {
        requestCreator = new RequestCreator(builder.mContext);
    }

    public static RxImageLoader with(Context context) {
        if (singleton == null) {
            synchronized (RxImageLoader.class) {
                if (singleton == null) {
                    singleton = new Builder(context).build();
                }
            }
        }
        return singleton;
    }

    public RxImageLoader load(String url) {
        this.mUrl = url;
        return singleton;
    }

    public void into(final ImageView imageView) {
        Observable
                .concat(
                        requestCreator.getImageFromMemory(mUrl),
                        requestCreator.getImageFromDisk(mUrl)
                        //        requestCreator.getImageFromNetwork(mUrl)
                )
                .first(new ImageBean(null, mUrl)).toObservable()
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    private int retryCount;
                    @Override
                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Throwable {
                        return throwableObservable
                                .flatMap((Throwable throwable) -> {
                                    if (++retryCount <= 3) {
                                        // When this Observable calls onNext, the original Observable will be retried (i.e. re-subscribed).
                                        Log.e("get error, retry count " ,retryCount+"-");
                                        return Observable.just("" + retryCount).delay(5,TimeUnit.SECONDS);
                                    }
                                    // Max retries hit. Just pass the error along.
                                    return Observable.error(throwable);
                                });
                    }
                })
                .subscribe(new Observer<ImageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ImageBean imageBean) {
                        Log.e("onNext", imageBean.getBitmap() + "--" + imageBean.getUrl());
                        if (imageBean.getBitmap() != null) {

                            imageView.setImageBitmap(imageBean.getBitmap());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("eeeeeeeee", e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.e("onComplete", "onComplete");
                    }
                });
//
//        Observable.concat(requestCreator.getImageFromMemory(mUrl),
//                        requestCreator.getImageFromDisk(mUrl),
//                        requestCreator.getImageFromNetwork(mUrl)
//                )
//
//                .subscribeOn(Schedulers.io())
//                .firstElement()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<ImageBean>() {
//                    @Override
//                    public void accept(ImageBean imageBean) throws Throwable {
//                        Log.e("imageBean", imageBean.getUrl());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Throwable {
//                        Log.e("throwable", throwable.getMessage());
//                    }
//                });

    }

    public static class Builder {

        private Context mContext;

        public Builder(Context mContext) {
            this.mContext = mContext;
        }

        public RxImageLoader build() {
            return new RxImageLoader(this);
        }
    }
}