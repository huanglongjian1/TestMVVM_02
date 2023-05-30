package com.android.test2mvvm.test1.fragment10.binding;

import androidx.databinding.DataBindingComponent;

public class ProductionComponent implements DataBindingComponent {
    private MyBindingAdapter mAdapter = new ProductionBindingAdapter();
    @Override
    public MyBindingAdapter getMyBindingAdapter() {
        return mAdapter;
    }
}