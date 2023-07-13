package com.android.test2mvvm.test5.fragment5.room;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.paging.PagedList;

import com.android.test2mvvm.util.ExecutorUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ArticleBoundaryCallback extends PagedList.BoundaryCallback<Article> {
    private String TAG = this.getClass().getName();
    private Application application;
    int index;

    public ArticleBoundaryCallback(Application application) {
        this.application = application;
    }

    /**
     * 当数据库为空时，回调该方法，请求第一页数据
     */
    @Override
    public void onZeroItemsLoaded() {
        super.onZeroItemsLoaded();
        Log.e(TAG, "onZeroItemsLoaded()");
        index = 0;
        getTopData();
    }

    /**
     * 加载数据库数据
     *
     * @param itemAtFront 数据库中的第一条数据
     */
    @Override
    public void onItemAtFrontLoaded(@NonNull Article itemAtFront) {
        super.onItemAtFrontLoaded(itemAtFront);
        Log.e(TAG, "onItemAtFrontLoaded()");
    }

    /**
     * 请求下一页数据，并且数据库中数据全部加载完毕
     *
     * @param itemAtEnd 数据库中的最后一条数据
     */
    @Override
    public void onItemAtEndLoaded(@NonNull Article itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);
        Log.e(TAG, "onItemAtEndLoaded()");
        index++;
        getTopAfterData(itemAtEnd);
    }

    /**
     * 没有数据的时候，加载第一页数据
     */
    private void getTopData() {
//        int since = 0;
//        RetrofitClient.getInstance()
//                .getApi()
//                .articlesData(ArticlesViewModel.PER_PAGE / 20)
//                .enqueue(new Callback<Articles>() {
//                    @Override
//                    public void onResponse(@NonNull Call<Articles> call, @NonNull Response<Articles> response) {
//                        if (response.body() != null) {
//                            Log.e("getTopData()", " response:" + response.body());
//                            insertArticles(response.body().data.datas);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(@NonNull Call<Articles> call, @NonNull Throwable t) {
//
//                    }
//                });
        ExecutorUtil.execute(new Runnable() {
            @Override
            public void run() {
                List<Article> list = new ArrayList<>();
                for (int i = index; i < index * 20 + 20; i++) {
                    //  int i1 = new Random().nextInt(Integer.MAX_VALUE);
                    Article articlea = new Article(i, "author" + i, "desc" + i, "title" + i);
                    list.add(articlea);
                }
                insertArticles(list);

            }
        });

    }

    /**
     * 获取下一页数据
     */
    private void getTopAfterData(Article article) {

//        RetrofitClient.getInstance()
//                .getApi()
//                .articlesData(ArticlesViewModel.PER_PAGE / 20)
//                .enqueue(new Callback<Articles>() {
//                    @Override
//                    public void onResponse(@NonNull Call<Articles> call, @NonNull Response<Articles> response) {
//                        if (response.body() != null) {
//                            Log.e("getTopAfterData()", " response:" + response.body());
//                            insertArticles(response.body().data.datas);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(@NonNull Call<Articles> call, @NonNull Throwable t) {
//
//                    }
//                });
        ExecutorUtil.execute(new Runnable() {
            @Override
            public void run() {
                List<Article> list = new ArrayList<>();
                for (int i = index; i < index * 20 + 20; i++) {
                    //  int i1 = new Random().nextInt(Integer.MAX_VALUE);
                    Article articlea = new Article(i, "author" + i, "desc" + i, "title" + i);
                    list.add(articlea);
                }
                insertArticles(list);

            }
        });


    }
    private void insertArticles(final List<Article> articles) {
        Observable.timer(0, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Throwable {
                        ArticleDatabase.getInstance(application).articleDao().insertArticles(articles);
                    }
                });
    }

}
