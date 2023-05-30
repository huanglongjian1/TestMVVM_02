package com.android.test2mvvm.test6.fragments.paging3;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.ListenableFuturePagingSource;

import com.android.test2mvvm.test6.fragments.paging3.bean.ArticleBean;
import com.android.test2mvvm.test6.fragments.paging3.bean.DatasBean;
import com.android.test2mvvm.util.Loge;
import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import retrofit2.Call;

public class Paging3DataSource_03 extends ListenableFuturePagingSource<Integer, DatasBean> {
    private ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    @NonNull
    @Override
    public ListenableFuture<LoadResult<Integer, DatasBean>> loadFuture(@NonNull LoadParams<Integer> loadParams) {
        Integer nextPageNumber = loadParams.getKey();
        if (nextPageNumber == null) {
            nextPageNumber = 0;//从第0页开始加载
        }
        Integer finalNextPageNumber = nextPageNumber;
        Loge.e(Thread.currentThread().getName() + "----loadFuture");
        ListenableFuture<LoadResult<Integer, DatasBean>> listenableFuture = Futures.transform(executorService.submit(new Callable<List<DatasBean>>() {
            @Override
            public List<DatasBean> call() throws Exception {
                Loge.e(Thread.currentThread().getName() + ":当前线程");
                Call<ArticleBean> call = RetrofitClient.getInstance().createApi(WanAndroidApi.class).getA(finalNextPageNumber);
                ArticleBean articleBean = call.execute().body();

                Loge.e(Thread.currentThread().getName() + "----call");
                return articleBean.getData().getDatas();
            }
        }), new Function<List<DatasBean>, LoadResult<Integer, DatasBean>>() {
            @Nullable
            @Override
            public LoadResult<Integer, DatasBean> apply(@Nullable List<DatasBean> input) {
                Loge.e(Thread.currentThread().getName() + "----apply");
                return new LoadResult.Page<>(input, finalNextPageNumber == 0 ? null : finalNextPageNumber - 1, input.isEmpty() ? null : finalNextPageNumber + 1);

            }
        }, executorService);


         ListenableFuture<LoadResult<Integer, DatasBean>> partialLoadResultFuture = Futures.catching(
                listenableFuture, Exception.class,
                new Function<Exception, LoadResult<Integer, DatasBean>>() {
                    @Nullable
                    @Override
                    public LoadResult<Integer, DatasBean> apply(@Nullable Exception input) {
                        Loge.e(Thread.currentThread().getName()+"Function<Exception, LoadResult<Integer, DatasBean>>()");
                        return null;
                    }
                }, executorService);


        return Futures.catching(partialLoadResultFuture,
                Exception.class, new Function<Exception, LoadResult<Integer, DatasBean>>() {
                    @Nullable
                    @Override
                    public LoadResult<Integer, DatasBean> apply(@Nullable Exception input) {
                        Loge.e(Thread.currentThread().getName()+"<Integer, DatasBean> apply(@Nullable Exception");
                        return null;
                    }
                }, executorService);


    }
}
