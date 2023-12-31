package com.android.test2mvvm.retrofit.http;

import com.android.test2mvvm.retrofit.base.ActivityLifeCycleEvent;
import com.android.test2mvvm.retrofit.enity.HttpResult;
import com.android.test2mvvm.retrofit.enity.User;
import com.android.test2mvvm.util.Loge;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.subjects.PublishSubject;

/**
 * //  ┏┓　　　┏┓
 * //┏┛┻━━━┛┻┓
 * //┃　　　　　　　┃
 * //┃　　　━　　　┃
 * //┃　┳┛　┗┳　┃
 * //┃　　　　　　　┃
 * //┃　　　┻　　　┃
 * //┃　　　　　　　┃
 * //┗━┓　　　┏━┛
 * //   ┃　　　┃   神兽保佑
 * //   ┃　　　┃   代码无BUG！
 * //   ┃　　　┗━━━┓
 * //   ┃　　　　　　　┣┓
 * //   ┃　　　　　　　┏┛
 * //   ┗┓┓┏━┳┓┏┛
 * //     ┃┫┫　┃┫┫
 * //     ┗┻┛　┗┻┛
 * <p>
 * Created by helin on 2016/10/10 11:32.
 */

public class HttpUtil {

    /**
     * 构造方法私有
     */
    private HttpUtil() {
        //手动创建一个OkHttpClient并设置超时时间
//        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
//        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
//        /**
//         * 对所有请求添加请求头
//         */
//        httpClientBuilder.addInterceptor(new Interceptor() {
//            @Override
//            public okhttp3.Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                okhttp3.Response originalResponse = chain.proceed(request);
//                return originalResponse.newBuilder().header("mobileFlag", "adfsaeefe").addHeader("type", "4").build();
//            }
//        });
//        retrofit = new Retrofit.Builder()
//                .client(httpClientBuilder.build())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .baseUrl(Url.BASE_URL)
//                .build();

    }

    /**
     * 在访问HttpMethods时创建单例
     */
    private static class SingletonHolder {
        private static final HttpUtil INSTANCE = new HttpUtil();
    }

    /**
     * 获取单例
     */
    public static HttpUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

//    public void getTopMovie(final ProgressSubscriber<List<Subject>> subscriber, int start, int count) {
//        movieService = retrofit.create(ApiService.class);
//        Api.getDefault().getTopMovie(start, count)
//                .map(new HttpResultFunc<List<Subject>>())
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//                        //显示Dialog
//                        subscriber.showProgressDialog();
//                    }
//                })
//                /**
//                 * 保证doOnSubscribe是在主线程执行
//                 */
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//        toSubscribe(Api.getDefault().getTopMovie(start, count), subscriber,"getMove");
//    }

//    public void userLogin(final ProgressSubscriber<UserEntity> subscriber, String name, String psw) {
//        Observable observer =  Api.getDefault().userLogin(name, psw, 4, "aaassdasd");
//                .map(new HttpResultFunc2<UserEntity>());
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//                        //显示Dialog
//                        subscriber.showProgressDialog();
//                    }
//                })
//                /**
//                 * 保证doOnSubscribe是在主线程执行
//                 */
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);

//        toSubscribe(observer, subscriber,"login");
//    }


    /**
     * 添加线程管理并订阅
     *
     * @param ob
     * @param subscriber
     * @param cacheKey     缓存kay
     * @param event        Activity 生命周期
     * @param isSave       是否缓存
     * @param forceRefresh 是否强制刷新
     */
    public <T> void toSubscribe(Observable ob, final ProgressSubscriber<T> subscriber, String cacheKey, final ActivityLifeCycleEvent event, final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject, boolean isSave, boolean forceRefresh) {
        //数据预处理
        ObservableTransformer<HttpResult<T>, T> result = RxHelper.<T>handleResult(event, lifecycleSubject);
        Observable observable = ob.compose(result);

        RetrofitCache.load(cacheKey, observable, isSave, forceRefresh)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Throwable {
                        subscriber.showProgressDialog();
                        Loge.e("展示进度条" + Thread.currentThread().getName());
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .retryWhen((Function<? super Observable<Throwable>, ? extends ObservableSource<?>>) new RetryWithDelay(3, 3))
                .subscribe(subscriber);


//        ob.subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//                        //显示Dialog和一些其他操作
//                        subscriber.showProgressDialog();
//                    }
//                })
//                //保证doOnSubscribe是在主线程执行
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
    }


    /**
     * 废弃
     * @param <T>
     */
//    private class HttpResultFunc2<T> implements Func1<HttpResult<T>, T> {
//        @Override
//        public T call(HttpResult<T> httpResult) {
//            Log.e("error", httpResult.getData().toString() + "");
//            if (httpResult.getCode() != 0) {
//                throw new ApiException(httpResult.getCode());
//            }
//            return httpResult.getData();
//        }
//    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
//    private class HttpResultFunc<T> implements Func1<MoveHttpResult<T>, T> {
//
//        @Override
//        public T call(MoveHttpResult<T> httpResult) {
////            if (httpResult.getResultCode() != 0) {
////                throw new ApiException(httpResult.getResultCode());
////            }
////            return httpResult.getData();
//
//            if (httpResult.getCount() == 0) {
//                throw new ApiException(100);
//            }
//            return httpResult.getSubjects();
//
//        }
//    }
}
