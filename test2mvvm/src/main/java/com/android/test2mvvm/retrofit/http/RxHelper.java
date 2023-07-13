package com.android.test2mvvm.retrofit.http;


import com.android.test2mvvm.retrofit.base.ActivityLifeCycleEvent;
import com.android.test2mvvm.retrofit.enity.HttpResult;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

/**
 * Created by helin on 2016/11/9 17:02.
 */

public class RxHelper {

    /**
     * 利用Observable.takeUntil()停止网络请求
     *
     * @param event
     * @param lifecycleSubject
     * @param <T>
     * @return
     */
    @NonNull
    public <T> ObservableTransformer<T, T> bindUntilEvent(@NonNull final ActivityLifeCycleEvent event, final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject) {
        return new ObservableTransformer<T, T>() {

            @Override
            public @NonNull ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                Observable<ActivityLifeCycleEvent> compareLifecycleObservable =
                        lifecycleSubject.takeWhile(new Predicate<ActivityLifeCycleEvent>() {
                            @Override
                            public boolean test(ActivityLifeCycleEvent activityLifeCycleEvent) throws Throwable {
                                return activityLifeCycleEvent.equals(event);
                            }
                        });
                return upstream.takeUntil(compareLifecycleObservable);
            }
        };
    }


    /**
     * @param <T>
     * @param lifecycleSubject
     * @return
     */
    public static <T> ObservableTransformer<HttpResult<T>, T> handleResult(final ActivityLifeCycleEvent event, final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject) {
        return new ObservableTransformer<HttpResult<T>, T>() {

            @Override
            public @NonNull ObservableSource<T> apply(@NonNull Observable<HttpResult<T>> upstream) {
                Observable<ActivityLifeCycleEvent> compareLifecycleObservable =
                        lifecycleSubject.takeWhile(new Predicate<ActivityLifeCycleEvent>() {
                            @Override
                            public boolean test(ActivityLifeCycleEvent activityLifeCycleEvent) throws Throwable {
                                return activityLifeCycleEvent.equals(event);
                            }
                        });
                return upstream.flatMap(new Function<HttpResult<T>, Observable<T>>() {

                    @Override
                    public Observable<T> apply(HttpResult<T> tHttpResult) throws Throwable {
                        if (tHttpResult.getCount() != 0) {
                            return createData(tHttpResult.getSubjects());
                        } else {
                            return Observable.error(new ApiException(tHttpResult.getCount()));
                        }
                    }
                }).takeUntil(compareLifecycleObservable).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread());

            }
        };
    }

    /**
     *
     *
     */


    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> emitter) throws Throwable {
                try {
                    emitter.onNext(data);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });

    }

}
