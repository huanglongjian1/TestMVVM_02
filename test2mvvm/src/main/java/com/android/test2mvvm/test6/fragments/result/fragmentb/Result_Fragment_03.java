package com.android.test2mvvm.test6.fragments.result.fragmentb;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.ResultFragmentBinding;
import com.android.test2mvvm.test4.base.BaseFragment;

public class Result_Fragment_03 extends BaseFragment<ResultFragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.result_fragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    Activity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    public static Fragment newInstance(Bundle args) {
        Fragment fragment = new Result_Fragment_03();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        activity = null;
    }
}
