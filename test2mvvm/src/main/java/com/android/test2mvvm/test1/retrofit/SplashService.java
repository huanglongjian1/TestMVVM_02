package com.android.test2mvvm.test1.retrofit;

import androidx.lifecycle.LiveData;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SplashService {

    @GET("HPImageArchive.aspx?format=js&idx=0&n=1")
    Call<BingImg> getBingImg();

    @GET("HPImageArchive.aspx?format=js&idx=0&n=1")
    LiveData<javabean> getBingImgLiveData();

    @GET("HPImageArchive.aspx?format=js&idx=0&n=1")
    Observable<javabean> getBingImgLiveData_observable();

    @GET("HPImageArchive.aspx?format=js&idx=0&n=1")
    LiveData<BingImg> getBingImgLiveData_BingImg();
}
