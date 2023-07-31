package com.android.test2mvvm.test6.test_fragment2.util;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.android.test2mvvm.test6.basebottomsheet.BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;

public abstract class Util_BaseFullBottomSheetFragment<T extends ViewDataBinding> extends BaseFullBottomSheetFragment {
    protected T binding;
    protected View root_view;
    private boolean isFirstLoad = true; // 是否第一次加载

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void onDataLazyLoad();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        root_view = binding.getRoot();
        return root_view;
    }

    public Util_BaseFullBottomSheetFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Loge.e(this.getClass().getSimpleName()+":onDestroy");
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            onDataLazyLoad();
            isFirstLoad = false;
        }
    }

}
