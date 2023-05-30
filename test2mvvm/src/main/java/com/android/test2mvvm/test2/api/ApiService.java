package com.android.test2mvvm.test2.api;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;

import com.android.test2mvvm.test1.retrofit.BingImg;
import com.android.test2mvvm.test2.bean.huochepiao_12306.LoginAysnSuggest;
import com.android.test2mvvm.test2.bean.music_163.Music_bean;
import com.android.test2mvvm.test2.bean.zhuangbi_bean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    public static final String URL_BAIDU = "http://www.baidu.com";

    @Headers("Cache-Control: max-age=1200")
    @GET(".")
    Observable<String> get_baidu();

    @GET("/s")
    Observable<String> get_baidu_s(@Query("wd") String wd);

    @FormUrlEncoded
    @POST("/s")
    Observable<String> post_baidu_s(@Field("wd") String wd);

    public static final String URL_BING = "https://cn.bing.com/";

    @GET("HPImageArchive.aspx?format=js&idx=0&n=1")
    LiveData<BingImg> getBingImgLiveData();

    @GET("HPImageArchive.aspx?format=js&idx=0&n=1")
    LiveData<BingImg.ImagesBean> getBingImgLiveData_ImagesBean();

    public static final String URL_ZHUANGBI = "https://zhuangbi.info/";

    @GET("search")
    LiveData<List<zhuangbi_bean>> get_zhuangbi_liveData(@Query("q") String q);

    @GET("search")
    Observable<List<zhuangbi_bean>> get_zhuangbi_Observable(@Query("q") String q);

    @GET("search")
    Observable<List<zhuangbi_bean>> get_zhuangbi_Observable_bean(@Query("q") String q);


    public static final String URL_12306 = "https://www.12306.cn/";

    @GET("/index/")
    Observable<String> get_12306();

    @GET("/passport/captcha/captcha-image")
    Observable<ResponseBody> get_12306_captcha_image(@QueryMap HashMap<String, String> hashMap);

    @GET("/otn/login/init")
    Observable<String> get_init();

    @GET("/otn/login/loginAysnSuggest")
    Observable<LoginAysnSuggest> get_LoginAysnSuggest();

    public static final String URL_ = "https://www.tngou.net/";

    @GET("api/food/list")
    Observable<String> get_tngou(@Query("id") int id);

    public static final String URL_163="https://api.uomg.com/";
    @FormUrlEncoded
    @POST("api/comments.163")
    Observable<Music_bean> postDataCall(@Field("format") String format);
}
