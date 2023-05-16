package com.android.test2mvvm.test1.fragment7;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IGetUrlService {

    @GET("HPImageArchive.aspx")
    Observable<ImageUrlBean> getUrl(@Query("format") String format,
                                    @Query("idx") int idx,
                                    @Query("n") int n);
}
