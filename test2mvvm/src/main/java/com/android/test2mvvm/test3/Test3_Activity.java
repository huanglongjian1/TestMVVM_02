package com.android.test2mvvm.test3;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.test2mvvm.R;
import com.android.test2mvvm.base.BaseActivity;
import com.android.test2mvvm.base.BaseObserver;
import com.android.test2mvvm.databinding.AppbarLayoutBinding;
import com.android.test2mvvm.test3.api.Api;
import com.android.test2mvvm.test3.rxjava.TokenLoader;
import com.android.test2mvvm.util.Constants;
import com.android.test2mvvm.util.Loge;
import com.android.test2mvvm.util.okhttp.httplib.retrofit2.RxSchedulers;
import com.android.test2mvvm.util.okhttp.httplib.retrofit2.ServiceFactory;
import com.google.android.material.appbar.AppBarLayout;

import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import kotlin.jvm.Synchronized;

@Route(path = Constants.TEST3_ACTIVITY)
public class Test3_Activity extends BaseActivity<Test3_ViewModel, AppbarLayoutBinding> {
    @Override
    protected int getContentViewId() {
        return R.layout.appbar_layout;
    }

    @Override
    protected void processLogic() {
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setLogo(R.drawable.ic_loading);
        binding.toolbar.setNavigationIcon(R.drawable.ic_launcher_background);

    }

    @Override
    protected void registerViewModel() {

    }

    @Override
    protected void initEvent() {
        binding.test2AppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {
                    Loge.e("到顶了，刷新数据");
                }
                int newalpha = 255 + verticalOffset;
                newalpha = newalpha < 0 ? 0 : newalpha;
                Loge.e(newalpha + "-");
                binding.test3Image.setAlpha(newalpha);
                binding.test3Image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ServiceFactory.getService(Api.URL_163, Api.class).get_163music("新歌榜", "json")
                                .compose(RxSchedulers.ioMain(Test3_Activity.this)).subscribe(new BaseObserver<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        Loge.e(s);
                                    }

                                    @Override
                                    public void onFailure(Throwable e) {
                                        Loge.e(e.getMessage() + "----e");
                                    }
                                });
                    }
                });
            }
        });
        binding.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e("aaaa");
                Observable observable = Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                        for (int i = 0; i < 10; i++) {
                            emitter.onNext(i);
                            Thread.sleep(500);
                        }

                        emitter.onComplete();
                    }
                }).subscribeOn(Schedulers.io());
                Observable observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                        for (int i = 100; i < 200; i++) {
                            emitter.onNext(i);
                        }
                        emitter.onComplete();
                    }
                });
                Observer observer = new Observer() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        Loge.e(o.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Loge.e("完成");
                    }
                };
                Observable observable2 = Observable.zip(observable, observable1, new BiFunction<Integer, Integer, String>() {
                    @Override
                    public String apply(Integer integer, Integer integer2) throws Throwable {
                        return String.valueOf(integer) + String.valueOf(integer2) + "合并";
                    }
                });
                observable2.subscribe(observer);

            }
        });
    }

    @Override
    protected void initData() {

    }

    private static final String ERROR_TOKEN = "error_token";
    private static final String ERROR_RETRY = "error_retry";

    private Observable<String> getUserObservable(final int index, final String token) {
        return Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.d(TAG, index + "使用token=" + token + "发起请求");
                //模拟根据Token去请求信息的过程。
                if (!TextUtils.isEmpty(token) && System.currentTimeMillis() - Long.valueOf(token) < 2000) {
                    e.onNext(index + ":" + token + "的用户信息");
                } else {
                    e.onError(new Throwable(ERROR_TOKEN));
                }
            }
        });
    }

    private void startRequest(final int index) {
        Observable<String> observable = Observable.defer(new Supplier<ObservableSource<String>>() {
            @Override
            public ObservableSource<String> get() throws Throwable {
                String cacheToken = TokenLoader.getInstance().getCacheToken();
                Log.d(TAG, index + "获取到缓存Token=" + cacheToken);
                return Observable.just(cacheToken);
            }
        }).flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String token) throws Exception {
                return getUserObservable(index, token);
            }
        }).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            private int mRetryCount = 0;

            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        Log.d(TAG, index + ":" + "发生错误=" + throwable + ",重试次数=" + mRetryCount);
                        if (mRetryCount > 0) {
                            return Observable.error(new Throwable(ERROR_RETRY));
                        } else if (ERROR_TOKEN.equals(throwable.getMessage())) {
                            mRetryCount++;
                            return TokenLoader.getInstance().getNetTokenLocked();
                        } else {
                            return Observable.error(throwable);
                        }
                    }
                });
            }
        });

        DisposableObserver<String> observer = new DisposableObserver<String>() {

            @Override
            public void onNext(String value) {
                Log.d(TAG, index + ":" + "收到信息=" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, index + ":" + "onError=" + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, index + ":" + "onComplete");
            }
        };
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    private static final String TAG = Test3_Activity.class.getSimpleName();

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}
