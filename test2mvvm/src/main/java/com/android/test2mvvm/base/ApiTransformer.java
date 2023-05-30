package com.android.test2mvvm.base;

import com.android.test2mvvm.R;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ApiTransformer {
    public static <T> ObservableTransformer<T, T> applySchedule() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
