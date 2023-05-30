package com.android.test2mvvm.test1.fragment10.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.core.Observer;

import com.android.test2mvvm.test1.fragment10.bean.News;
import com.android.test2mvvm.test1.fragment10.network.GetRequest_Interface;
import com.android.test2mvvm.util.Loge;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends ViewModel {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.tianapi.com/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);


    //当数据请求成功回调
    protected MutableLiveData<List<News.NewslistBean>> newsList = new MutableLiveData<>();

    public void request() {
        //RxJava切换线程
        Observable.create(new ObservableOnSubscribe<News>() {
                    @Override
                    public void subscribe(@NonNull final ObservableEmitter<News> emitter) throws Exception {
                        // Retrofit进行网络请求

                        Call<News> call = request.getCall();

                        call.enqueue(new Callback<News>() {
                            @Override
                            public void onResponse(Call<News> call, Response<News> response) {
                                News news = response.body();
                                emitter.onNext(news); //将news通过发射器发送给Observer
                            }

                            @Override
                            public void onFailure(Call<News> call, Throwable t) {
                                System.out.println("连接失败");
                                System.out.println(t.getMessage());
                            }
                        });
                    }
                })
                .subscribeOn(Schedulers.io())  // 为上面代码分配io线程
                .observeOn(AndroidSchedulers.mainThread())   // 为下面代码分配主线程
                .subscribe(new Observer<News>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull News news) {
                        if (news != null) {
                            newsList.postValue(news.getNewslist());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void request_observable() {
        Loge.e("request_observable 开始了");
        request.getCallObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<News>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull News news) {
                        if (news != null) {
                            newsList.postValue(news.getNewslist());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public MutableLiveData<List<News.NewslistBean>> getNewsList() {
        return newsList;
    }

    private MutableLiveData<String> liveEvent;
    private MediatorLiveData<String> testLiveData;

    public MainViewModel() {
        testLiveData = new MediatorLiveData<>();
        getNameFromServer();
        testLiveData.addSource(liveEvent, new androidx.lifecycle.Observer<String>() {
            @Override
            public void onChanged(String s) {
                testLiveData.postValue(s + " gong");
            }
        });



    }

    public MediatorLiveData<String> getTestLiveData() {
        return testLiveData;
    }

    public MutableLiveData<String> getNameFromServer() {
        if (liveEvent == null) {
            liveEvent = new MutableLiveData<>();
        }
        liveEvent.setValue("alan");
        return liveEvent;
    }
}
