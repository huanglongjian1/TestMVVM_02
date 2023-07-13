package com.android.test2mvvm.test4;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity_test<VDB extends ViewDataBinding> extends AppCompatActivity {
    protected abstract int getContentViewId();
    protected VDB binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,getContentViewId());
    }
}
