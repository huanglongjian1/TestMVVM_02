package com.android.test2mvvm.test4.fragment1;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.test2mvvm.R;
import com.android.test2mvvm.adapter.BaseRecyclerViewAdapter;
import com.android.test2mvvm.databinding.Test4Fragment1Binding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test4.fragment2.User;
import com.android.test2mvvm.util.Loge;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;


public class Test4_fragment1 extends BaseFragment<Test4Fragment1Binding> {

    @Override
    protected int getLayoutId() {
        return R.layout.test4_fragment1;
    }

    @Override
    protected void initView() {
        Loge.e("初始化");
        Recycler_Adapter recycler_adapter = new Recycler_Adapter(getContext());
        for (int i = 0; i < 5; i++) {
            recycler_adapter.getList().add("A+" + i);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.test4Fragment1RecyclerView.setLayoutManager(linearLayoutManager);
        binding.setAdapter(recycler_adapter);

//        User user = new User("AAA", 123456);
//        binding.setU(user);
//        Observable.interval(2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) throws Throwable {
//                user.setName("AAA" + aLong);
//            }
//        });
        binding.test4Fragment1SwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.test4Fragment1SwipeRefreshLayout.setRefreshing(false);
                for (int i = 0; i < 3; i++) {
                    recycler_adapter.getList().add("新增数据" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                binding.test4Fragment1SwipeRefreshLayout.setRefreshing(false);
                recycler_adapter.notifyDataSetChanged();
            }
        });
        //设置数据观察者
        AdapterObserver observer = new AdapterObserver();
        observer.onAttach(binding.test4Fragment1RecyclerView);
        binding.test4Fragment1RecyclerView.onAttach(binding.test4Fragment1Tv, observer);

        recycler_adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(Object data, int position) {
                if (position == 0) {
                    recycler_adapter.getList().clear();
                    recycler_adapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    protected void initData() {

    }


}
