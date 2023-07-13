package com.android.test2mvvm.test5.fragment7.util;


import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * CustomDisposable
 *
 * @author llw
 */
public class CustomDisposable {

    private static final CompositeDisposable compositeDisposable = new CompositeDisposable();

    /**
     * Flowable
     *
     * @param flowable
     * @param consumer
     * @param <T>
     */
    public static <T> void addDisposable(Flowable<T> flowable, Consumer<T> consumer) {
        compositeDisposable.add(flowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer));
    }

    /**
     * Completable
     *
     * @param completable
     * @param action
     * @param <T>
     */
    public static <T> void addDisposable(Observable completable, Consumer consumer) {
        compositeDisposable.add(completable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer));
    }
}