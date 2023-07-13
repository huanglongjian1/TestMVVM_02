package com.android.test2mvvm.test5.fragment5.wanan;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.android.test2mvvm.retrofit2.api.RetrofitUtils;
import com.android.test2mvvm.test5.fragment5.wanan.api.API;
import com.android.test2mvvm.test5.fragment5.wanan.api.bean.DatasBean;
import com.android.test2mvvm.test5.fragment5.wanan.api.bean.Wan_Bean;
import com.android.test2mvvm.util.Loge;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PagingDataSource extends PageKeyedDataSource<String, DatasBean> {
    API api = RetrofitUtils.getRetrofitBuilder(API.WANANDROID_BASEURL).build().create(API.class);
    private int mPage = 1;
  public static final   int pagesize = 20;
    @Override
    public void loadAfter(@NonNull LoadParams<String> loadParams, @NonNull LoadCallback<String, DatasBean> loadCallback) {
        mPage++;
        Loge.e(mPage + ":页");
        api.get_json(mPage).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Wan_Bean>() {
                    @Override
                    public void accept(Wan_Bean wan_bean) throws Throwable {
                        Loge.e(loadParams.key);
                        loadCallback.onResult(wan_bean.getData().getDatas(), loadParams.key);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Loge.e(throwable.getMessage());
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> loadParams, @NonNull LoadCallback<String, DatasBean> loadCallback) {
        Loge.e("--loadBefore-->" + loadParams.key);
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> loadInitialParams, @NonNull LoadInitialCallback<String, DatasBean> loadInitialCallback) {
        api.get_json(mPage).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Wan_Bean>() {
                    @Override
                    public void accept(Wan_Bean wan_bean) throws Throwable {
                        loadInitialCallback.onResult(wan_bean.getData().getDatas(), "before", "after");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Loge.e(throwable.getMessage());
                    }
                });
    }
}
