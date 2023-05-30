package com.android.test2mvvm.test1.fragment8.adapter;

import android.content.Context;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test1Fragment8RecyclerviewItemBinding;
import com.android.test2mvvm.test1.fragment5.User;
import com.android.test2mvvm.test1.fragment8.base.BaseBindingAdapter;

public class SimpleDataBindingAdapter extends BaseBindingAdapter<User, Test1Fragment8RecyclerviewItemBinding> {

    public SimpleDataBindingAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.test1_fragment8_recyclerview_item;
    }

    @Override
    protected void onBindItem(Test1Fragment8RecyclerviewItemBinding binding, User item) {
        binding.setUser(item);
    }
}
