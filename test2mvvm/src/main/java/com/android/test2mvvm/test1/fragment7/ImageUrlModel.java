package com.android.test2mvvm.test1.fragment7;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ImageUrlModel {

    private HttpUtil mHttpUtil;

    public ImageUrlModel() {
        mHttpUtil = HttpUtil.getInstance();
    }

    public void getImageUrl(String format, int idx, int n, final GetUrlCallback callback) {
        mHttpUtil.getImageUrl(format, idx, n)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImageUrlBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ImageUrlBean imageUrlBean) {
                        UrlData<ImageUrlBean.UrlBean> data =
                                new UrlData<>(imageUrlBean.getImages().get(0), null);
                        callback.handleUrl(data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        UrlData<ImageUrlBean.UrlBean> data = new UrlData<>(null, e.getMessage());
                        callback.handleUrl(data);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
