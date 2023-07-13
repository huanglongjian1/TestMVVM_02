package com.android.test2mvvm.retrofit.http;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * Created by helin on 2016/10/10 15:50.
 */

public interface ProgressCancelListener {
    void onSubscribe(@NonNull Disposable d);

    void onCancelProgress();
}
