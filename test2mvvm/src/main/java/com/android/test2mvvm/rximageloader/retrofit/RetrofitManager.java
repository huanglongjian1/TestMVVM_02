package com.android.test2mvvm.rximageloader.retrofit;

import android.provider.CalendarContract;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    Retrofit retrofit;

    private RetrofitManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(GetImage.BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) //添加Rxjava
                .addConverterFactory(GsonConverterFactory.create()) // <span style="font-family: Arial, Helvetica, sans-serif;">定义转化器 可以将结果返回一个json格式</span>
                .build();
    }

    public static final RetrofitManager Instance = new RetrofitManager();

    public static RetrofitManager getInstance() {
        return Instance;
    }

    public GetImage creatserver() {
        return retrofit.create(GetImage.class);
    }

}
