package com.android.test2mvvm.test3.rxjava;

import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.processors.PublishProcessor;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class TokenLoader {

    private static final String TAG = TokenLoader.class.getSimpleName();

    private AtomicBoolean mRefreshing = new AtomicBoolean(false);
    private PublishSubject<String> mPublishSubject;
    private Observable<String> mTokenObservable;
    private PublishProcessor<String> mPublishProcessor;
    private Flowable<String> mTokenFlow;

    private TokenLoader() {
        mPublishSubject = PublishSubject.create();
        mTokenObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Thread.sleep(1000);
                Log.d(TAG, "发送Token");
                e.onNext(String.valueOf(System.currentTimeMillis()));
            }
        }).doOnNext(new Consumer<String>() {
            @Override
            public void accept(String token) throws Exception {
                Log.d(TAG, "存储Token=" + token);
                Store.getInstance().setToken(token);
                mRefreshing.set(false);
            }
        }).doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mRefreshing.set(false);
            }
        }).subscribeOn(Schedulers.io()).serialize();
        mPublishProcessor = PublishProcessor.create();
        mTokenFlow = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                Thread.sleep(1000);
                Log.d(TAG, "发送Token");
                e.onNext(String.valueOf(System.currentTimeMillis()));
            }
        }, BackpressureStrategy.BUFFER).doOnNext(new Consumer<String>() {
            @Override
            public void accept(String token) throws Exception {
                Log.d(TAG, "存储Token=" + token);
                Store.getInstance().setToken(token);
                mRefreshing.set(false);
            }
        }).doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mRefreshing.set(false);
            }
        }).subscribeOn(Schedulers.io());
    }

    public static TokenLoader getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final TokenLoader INSTANCE = new TokenLoader();
    }

    public String getCacheToken() {
        return Store.getInstance().getToken();
    }

    public Observable<String> getNetTokenLocked() {
        if (mRefreshing.compareAndSet(false, true)) {
            Log.d(TAG, "没有请求，发起一次新的Token请求");
            startTokenRequest();
        } else {
            Log.d(TAG, "已经有请求，直接返回等待");
        }
        return mPublishSubject;
    }

    private void startTokenRequest() {
        mTokenObservable.subscribe(mPublishSubject);
    }

    public Flowable<String> getNetTokenFlowLocked() {
        if (mRefreshing.compareAndSet(false, true)) {
            Log.d(TAG, "没有请求，发起一次新的Token请求");
            startTokenFlowRequest();
        } else {
            Log.d(TAG, "已经有请求，直接返回等待");
        }
        return mPublishProcessor;
    }

    private void startTokenFlowRequest() {
        mTokenFlow.subscribe(mPublishProcessor);
    }

}
