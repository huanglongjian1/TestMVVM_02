package com.android.test2mvvm.test5.fragment5.wanan.api;

import com.android.test2mvvm.test5.fragment5.wanan.api.bean.Wan_Bean;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {
    public static final String WANANDROID_BASEURL = "https://wanandroid.com/wenda/";

    @GET("list/{num}/json")
    Observable<Wan_Bean> get_json(@Path("num") int num);
}
