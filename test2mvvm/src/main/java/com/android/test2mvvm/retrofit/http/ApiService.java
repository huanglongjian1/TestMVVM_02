package com.android.test2mvvm.retrofit.http;


import com.android.test2mvvm.retrofit.enity.HttpResult;
import com.android.test2mvvm.retrofit.enity.Subject;
import com.android.test2mvvm.retrofit.enity.UserEntity;


import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by helin on 2016/10/9 17:09.
 */

public interface ApiService {
    @GET("/student/mobileRegister")
    Observable<HttpResult<UserEntity>> login(@Query("phone") String phone, @Query("password") String psw);


    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);

    @GET("top250")
    Observable<HttpResult<Subject>> getUser(@Query("touken") String touken);

    public static final String BAIDU = "http://www.baidu.com/";

    @GET("/")
    Observable<String> get_baidu();

}
