package com.android.test2mvvm.test1.fragment10.binding;

import androidx.databinding.DataBindingComponent;

public class TestComponent implements DataBindingComponent {
    private MyBindingAdapter mAdapter = new TestBindingAdapter();
    @Override
    public MyBindingAdapter getMyBindingAdapter() {
        return mAdapter;
    }
}