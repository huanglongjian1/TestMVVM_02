package com.android.test2mvvm.test6.fragments.paging3;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.android.kotlin.MyKotlin;
import com.android.mykotlin.Kotlinctivity;
import com.android.test2mvvm.R;
import com.android.test2mvvm.base.BaseObserver;
import com.android.test2mvvm.databinding.Paging3FragmentBinding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test6.fragments.paging3.bean.ArticleBean;
import com.android.test2mvvm.util.Constants;
import com.android.test2mvvm.util.Loge;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class Paging3_Fragment extends BaseFragment<Paging3FragmentBinding> {
    private final String TAG = "MainActivity";
    private Paging3Adapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.paging3_fragment;
    }

    @Override
    protected void initView() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new Paging3Adapter();
        binding.recyclerViewPaging3Test6.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        binding.recyclerViewPaging3Test6.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewPaging3Test6.setAdapter(adapter);

        Paging3ViewModel viewModel = new ViewModelProvider(this).get(Paging3ViewModel.class);

        viewModel.getArticleData().observe(this, pagingData -> adapter.submitData(getLifecycle(), pagingData));

        binding.paging3FragmentTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    ARouter.getInstance().build("/mykotlin/Kotlinctivity").navigation();
                ARouter.getInstance().build(Constants.TEST5_ACTIVITY).navigation();
            }
        });
    }

    ArticleBean articleBean;
    CountDownLatch latch = new CountDownLatch(1);

    @Override
    protected void initData() {
        Loge.e("A");
        Observable<ArticleBean> observable = RetrofitClient.getInstance().createApi(WanAndroidApi.class).getA_Observable(0);
        observable.subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .subscribe(new BaseObserver<ArticleBean>() {
                    @Override
                    public void onSuccess(ArticleBean articleBean) {
                        Loge.e("B");
                        Paging3_Fragment.this.articleBean = articleBean;
                        latch.countDown();
                    }

                    @Override
                    public void onFailure(Throwable e) {

                    }
                });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Loge.e("+++"+articleBean.toString() );
    }


}
