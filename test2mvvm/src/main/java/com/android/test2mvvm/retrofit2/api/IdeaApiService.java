package com.android.test2mvvm.retrofit2.api;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface IdeaApiService {
    public static final String BAIDU = "http://www.baidu.com/";

    @GET("/")
    Observable<String> get_baidu();
}
