package com.android.test2mvvm.test3.api;

import javax.annotation.Generated;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    public static final String URL_163 = "https://api.uomg.com/api/";

    @GET("rand.music")
    Observable<String> get_163music(@Query("sort") String sort, @Query("format") String format);

}
