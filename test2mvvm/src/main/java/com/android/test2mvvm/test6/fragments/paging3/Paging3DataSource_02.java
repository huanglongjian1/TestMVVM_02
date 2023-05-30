package com.android.test2mvvm.test6.fragments.paging3;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingSource;

import com.android.test2mvvm.base.BaseObserver;
import com.android.test2mvvm.test6.fragments.paging3.bean.ArticleBean;
import com.android.test2mvvm.test6.fragments.paging3.bean.DatasBean;
import com.android.test2mvvm.util.ExecutorUtil;
import com.android.test2mvvm.util.Loge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.coroutines.Continuation;
import retrofit2.Call;

public class Paging3DataSource_02 extends PagingSource<Integer, DatasBean> {
    @Nullable
    @Override
    public Object load(@NonNull LoadParams<Integer> loadParams, @NonNull Continuation<? super LoadResult<Integer, DatasBean>> continuation) {
        Loge.e(Thread.currentThread().getName() + "=============");
        int page = 0;
        if (loadParams.getKey() != null) {
            page = loadParams.getKey();
        }
        List<DatasBean> datas = new ArrayList<>();

        //获取网络数据
        ArticleBean result = getbean(page);
        int nextKey = page+1;
        if (result != null) {
            datas.addAll(result.getData().getDatas());
            if (result.getData().getCurPage() == result.getData().getPageCount()) {
                nextKey = 0;
            } else {
                nextKey = page + 1;
            }
        } else {
            DatasBean datasBean = new DatasBean();
            datasBean.setDesc("网络不好");
            datasBean.setPublishTime(System.currentTimeMillis());
            datasBean.setType(1);
            datasBean.setAuthor("没有取到数据，请重试");
            datas.add(datasBean);
        }

        //如果能够往上加载更多就设置该参数，否则不设置
        String prevKey = null;
        //加载下一页的key 如果传null就阐明到底了
        return new LoadResult.Page<>(datas, prevKey, nextKey);

    }

    public ArticleBean getbean(int page) {
        Call<ArticleBean> observable = RetrofitClient.getInstance().createApi(WanAndroidApi.class).getA(page);
        ArticleBean bean = ExecutorUtil.submit(new Callable<ArticleBean>() {
            @Override
            public ArticleBean call() throws Exception {
                return observable.execute().body();
            }
        });
        return bean;
    }
}
