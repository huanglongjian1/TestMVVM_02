package com.android.test2mvvm.retrofit2.api;

import com.android.test2mvvm.Test2_App;
import com.android.test2mvvm.retrofit.util.LogUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitUtils {
    public static OkHttpClient.Builder getOkHttpClientBuilder() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //  LogUtils.e("OKHttp-----", URLDecoder.decode(message, "utf-8"));
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File cacheFile = new File(Test2_App.getInstance().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        return new OkHttpClient.Builder()
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                // .addInterceptor(new HttpHeaderInterceptor())
                // .addNetworkInterceptor(new HttpCacheInterceptor())
                // .sslSocketFactory(SslContextFactory.getSSLSocketFactoryForTwoWay())  // https认证 如果要使用https且为自定义证书 可以去掉这两行注释，并自行配制证书。
                // .hostnameVerifier(new SafeHostnameVerifier())
                .cache(cache);
    }

    public static Retrofit.Builder getRetrofitBuilder(String baseUrl) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        OkHttpClient okHttpClient = RetrofitUtils.getOkHttpClientBuilder().build();
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(baseUrl);
    }
}

