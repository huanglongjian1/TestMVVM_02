package com.android.test2mvvm.test1.fragment10;





import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.test2mvvm.R;
import com.android.test2mvvm.Test2_App;
import com.android.test2mvvm.databinding.Test1Fragment10Binding;
import com.android.test2mvvm.test1.fragment10.adapter.NewsRecyclerAdapter;
import com.android.test2mvvm.test1.fragment10.bean.News;
import com.android.test2mvvm.test1.fragment10.binding.ProductionComponent;
import com.android.test2mvvm.test1.fragment10.binding.TestComponent;
import com.android.test2mvvm.test1.fragment10.viewmodel.MainViewModel;
import com.android.test2mvvm.util.Loge;

import java.util.List;

public class Test1_Fragment10 extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private Test1Fragment10Binding binding;

    private MainViewModel viewModel;

    private NewsRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.test1_fragment10, container, false);
        // binding=DataBindingUtil.setContentView(getActivity(),R.layout.test1_fragment10);
        init();
        initData();
        return binding.getRoot();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewModel.request_observable();

                binding.test1Fragment10SwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int mSpace;

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.bottom = mSpace;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = mSpace;
            }
        }

        public SpaceItemDecoration(int space) {
            this.mSpace = space;
        }
    }

    private void init() {
        //初始化ViewModel
        viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        viewModel.request();
        Log.i("sjh", "我是你爸爸");
    }

    private void initData() {
        binding.setModel(viewModel);
        //数据请求
        viewModel.getNewsList().observe(getViewLifecycleOwner(), new Observer<List<News.NewslistBean>>() {
            @Override
            public void onChanged(List<News.NewslistBean> newslistBeans) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(binding.getRoot().getContext());
                binding.recyclerView.setLayoutManager(linearLayoutManager);
                adapter = new NewsRecyclerAdapter(binding.getRoot().getContext());
                binding.recyclerView.addItemDecoration(new SpaceItemDecoration(5));
                binding.recyclerView.setAdapter(adapter);
                adapter.addAll(newslistBeans);
                adapter.setOnItemClickListener(new NewsRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClickListener(News.NewslistBean news) {
                        Loge.e(news.toString());
                    }
                });
            }
        });
        binding.test1Fragment10Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getNameFromServer().setValue("新数据");
            }
        });
        viewModel.getTestLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Loge.e(s);
            }
        });
        binding.test1Fragment10SwipeRefreshLayout.setOnRefreshListener(this);
        binding.setPresenter(new P());

    }

    public class P {
        public void onClickSimpleDemo(View view) {
            Loge.e("被点击了");
            if (Test2_App.isTest) {
                DataBindingUtil.setDefaultComponent(new ProductionComponent());
            } else {
                DataBindingUtil.setDefaultComponent(new TestComponent());
            }

            Test2_App.isTest = !Test2_App.isTest;


        }
    }
}
