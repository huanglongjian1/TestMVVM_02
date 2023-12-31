package com.android.test2mvvm.test1.fragment10.network;

import com.android.test2mvvm.test1.fragment10.bean.News;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetRequest_Interface {
    @GET("social/index?key=d44ec13840090282a1af867dec47bbbd&num=50")
    Call<News> getCall();
    // 注解里传入 网络请求 的部分URL地址
    // Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
    // 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
    // getCall()是接受网络请求数据的方法
    @GET("social/index?key=d44ec13840090282a1af867dec47bbbd&num=50")
    Observable<News> getCallObservable();
}
