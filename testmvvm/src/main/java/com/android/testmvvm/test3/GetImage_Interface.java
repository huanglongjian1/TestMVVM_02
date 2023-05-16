package com.android.testmvvm.test3;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface GetImage_Interface {
    @GET("api/mobil.girl?type=json")
    Observable<ModelGirl> getPic();
}