package com.android.test2mvvm.retrofit.http;


import com.android.test2mvvm.retrofit.enity.HttpResult;
import com.android.test2mvvm.retrofit.enity.User;
import com.android.test2mvvm.retrofit.util.Hawk;
import com.android.test2mvvm.util.Loge;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by helin on 2016/11/10 10:41.
 */

public class RetrofitCache {
    /**
     * @param cacheKey     缓存的Key
     * @param fromNetwork
     * @param isSave       是否缓存
     * @param forceRefresh 是否强制刷新
     * @param <T>
     * @return
     */
    public static <T> Observable<T> load(final String cacheKey,
                                         Observable<T> fromNetwork,
                                         boolean isSave, boolean forceRefresh) {
        Observable<T> fromCache = Observable.create(new ObservableOnSubscribe<T>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<T> emitter) throws Throwable {
                T cache = (T) new Hawk() {
                    @Override
                    protected Type getType() {
                        return new TypeToken<HttpResult<User>>() {
                        }.getType();

                    }
                }.get(cacheKey);
                Loge.e(cache.toString());
                if (cache != null) {
                    emitter.onNext(cache);

                } else {
                    emitter.onComplete();
                }

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        //是否缓存
        if (isSave) {
            /**
             * 这里的fromNetwork 不需要指定Schedule,在handleRequest中已经变换了
             */
            fromNetwork = (Observable<T>) fromNetwork.map(new Function<T, T>() {

                @Override
                public T apply(T t) throws Throwable {
                    return t;
                }
            });
        }
        //强制刷新
        if (forceRefresh) {
            return fromNetwork;
        } else {
//            return Observable.concat(fromCache, fromNetwork).first();
            return Observable.concat(fromCache, fromNetwork).takeWhile(new Predicate<T>() {
                @Override
                public boolean test(T t) throws Throwable {
                    Loge.e("查询数据");
                    return t != null;

                }
            });
        }
    }


}
