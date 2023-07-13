package com.android.test2mvvm.test5.fragment5.wanan;

import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test5Fragment5Binding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test5.fragment5.wanan.api.bean.DatasBean;
import com.android.test2mvvm.util.Loge;

public class Wanan_Fragment extends BaseFragment<Test5Fragment5Binding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test5_fragment5;
    }

    @Override
    protected void initView() {
        PagingAdapter pagingAdapter = new PagingAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.test5Fragment5Rv.setLayoutManager(linearLayoutManager);
        binding.test5Fragment5Rv.setAdapter(pagingAdapter);
        binding.test5Fragment5Rv.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);

        binding.test5Fragment5Rv.addItemDecoration(dividerItemDecoration);
        pagingViewModel.getPagedList().observe(this, new Observer<PagedList<DatasBean>>() {
            @Override
            public void onChanged(PagedList<DatasBean> datasBeans) {
                pagingAdapter.submitList(datasBeans);
            }
        });
        binding.chronometer.setBase(SystemClock.elapsedRealtime());
        binding.chronometer.setFormat("计时开始 %s");
        binding.chronometer.start();
        binding.chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            int i=0;
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                Loge.e(i++ +"----");
            }
        });
        binding.chronometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.chronometer.stop();
            }
        });
    }

    PagingViewModel pagingViewModel;

    @Override
    protected void initData() {
        pagingViewModel = new ViewModelProvider(this).get(PagingViewModel.class);
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.chronometer.stop();
    }
}
