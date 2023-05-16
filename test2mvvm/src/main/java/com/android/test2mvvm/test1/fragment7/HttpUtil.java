package com.android.test2mvvm.test1.fragment7;

import android.util.Log;

import androidx.annotation.NonNull;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtil {

    private Retrofit mRetrifit;

    private HttpUtil() {
        mRetrifit = new Retrofit.Builder()
                .baseUrl(ImageUrlBean.UrlBean.BASE_IMAGE_ADDRESS_URL)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Observable<ImageUrlBean> getImageUrl(String format, int idx, int n) {
        return getRetrofitService(IGetUrlService.class)
                .getUrl(format, idx, n);
    }
    public <T> T getRetrofitService(Class<T> tClass){

        return mRetrifit.create(tClass);
    }

    public static HttpUtil getInstance() {
        return Holder.mInstance;
    }

    private static class Holder {
        private static final HttpUtil mInstance = new HttpUtil();
    }

    private OkHttpClient getOkHttpClient() {
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String s) {
                Log.e("zcb", "OkHttp====Message:" + s);
            }
        });
        loggingInterceptor.setLevel(level);
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder();
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }

}