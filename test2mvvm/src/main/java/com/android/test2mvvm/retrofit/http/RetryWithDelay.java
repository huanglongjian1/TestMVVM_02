package com.android.test2mvvm.retrofit.http;

import com.android.test2mvvm.util.Loge;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;

public class RetryWithDelay implements
        Function<Observable<? extends Throwable>, Observable<?>> {

    private final int maxRetries;
    private final int retryDelayMillis;
    private int retryCount;

    public RetryWithDelay(int maxRetries, int retryDelayMillis) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
    }

    @Override
    public Observable<?> apply(Observable<? extends Throwable> observable) {
        return observable
                .flatMap(new io.reactivex.rxjava3.functions.Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Throwable {
                        if (++retryCount <= maxRetries) {
                            // When this Observable calls onNext, the original Observable will be retried (i.e. re-subscribed).
                            Loge.e("get error, it will try after " + retryDelayMillis
                                    + " millisecond, retry count " + retryCount);
                            return Observable.timer(retryDelayMillis,
                                    TimeUnit.MILLISECONDS);
                        }
                        // Max retries hit. Just pass the error along.
                        Loge.e("重试次数超过3次");
                        return Observable.error(throwable);
                    }
                });
    }
}
