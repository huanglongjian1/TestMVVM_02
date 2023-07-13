package com.android.test2mvvm.test5.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BindingViewHolder<VDB extends ViewDataBinding> extends RecyclerView.ViewHolder {
    public VDB mBinding;
    public BindingViewHolder(@NonNull VDB binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public VDB getBinding() {
        return mBinding;
    }
}
