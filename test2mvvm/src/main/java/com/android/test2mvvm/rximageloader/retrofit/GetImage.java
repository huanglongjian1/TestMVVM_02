package com.android.test2mvvm.rximageloader.retrofit;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetImage {
    @GET("{url}")
    Observable<ResponseBody> getimage(@Path("url") String url);

    String BASE_URL = "http://img2.baa.bitautotech.com/";
}
