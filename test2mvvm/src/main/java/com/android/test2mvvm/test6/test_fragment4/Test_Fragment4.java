package com.android.test2mvvm.test6.test_fragment4;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestFragment02Binding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test6.test_fragment3.Test_Fragment3;
import com.android.test2mvvm.test6.test_fragment4.one.One_Fragment;
import com.android.test2mvvm.test6.test_fragment4.three.Three_Fragment;
import com.android.test2mvvm.test6.test_fragment4.two.Two_Fragment;

public class Test_Fragment4 extends BaseFragment<TestFragment02Binding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_fragment_02;
    }

    public static Fragment newInstance(String name) {
        Bundle bundle = new Bundle();
        bundle.putString(Test_Fragment4.class.getSimpleName(), name);
        Fragment fragment = new Test_Fragment4();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView() {
        binding.test6FragmentBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new One_Fragment().show(getChildFragmentManager(), One_Fragment.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Two_Fragment().show(getChildFragmentManager(),Two_Fragment.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Three_Fragment().show(getChildFragmentManager(),Three_Fragment.class.getSimpleName());
            }
        });
    }

    @Override
    protected void initData() {

    }
}
