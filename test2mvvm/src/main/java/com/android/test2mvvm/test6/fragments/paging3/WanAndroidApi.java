package com.android.test2mvvm.test6.fragments.paging3;

import com.android.test2mvvm.test6.fragments.paging3.bean.ArticleBean;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WanAndroidApi {
    @GET("article/list/{page}/json")
    Call<ArticleBean> getA(@Path("page") int page);

    @GET("article/list/{page}/json")
    ArticleBean getArticleBean(@Path("page") int page);

    @GET("article/list/{page}/json")
    Observable<ArticleBean> getA_Observable(@Path("page") int page);
}
