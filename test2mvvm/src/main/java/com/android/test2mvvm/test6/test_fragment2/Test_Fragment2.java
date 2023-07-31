package com.android.test2mvvm.test6.test_fragment2;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestFragment02Binding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test6.test_fragment2.eight.Eight_Fragment;
import com.android.test2mvvm.test6.test_fragment2.five.Five_Fragment;
import com.android.test2mvvm.test6.test_fragment2.four.F4_fragment;
import com.android.test2mvvm.test6.test_fragment2.four.Four_Fragment;
import com.android.test2mvvm.test6.test_fragment2.night.Night_Fragment;
import com.android.test2mvvm.test6.test_fragment2.one.One_Fragment;
import com.android.test2mvvm.test6.test_fragment2.senven.Senven_Fragment;
import com.android.test2mvvm.test6.test_fragment2.six.Six_Fragment;
import com.android.test2mvvm.test6.test_fragment2.ten.Ten_Fragment;
import com.android.test2mvvm.test6.test_fragment2.three.F3_Fragment;
import com.android.test2mvvm.test6.test_fragment2.three.Three_Fragment;
import com.android.test2mvvm.test6.test_fragment2.two.Two_Fragment;

public class Test_Fragment2 extends BaseFragment<TestFragment02Binding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_fragment_02;
    }

    public static Fragment newInstance(String name) {
        Bundle bundle = new Bundle();
        bundle.putString(Test_Fragment2.class.getSimpleName(), name);
        Fragment fragment = new Test_Fragment2();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView() {
        binding.test6FragmentBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                One_Fragment one_fragment = new One_Fragment();
                one_fragment.show(getChildFragmentManager(), One_Fragment.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Two_Fragment().show(getChildFragmentManager(), Two_Fragment.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Three_Fragment().show(getChildFragmentManager(), Three_Fragment.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  new F3_Fragment().show(getChildFragmentManager(),F3_Fragment.class.getSimpleName());
                // new Four_Fragment().show(getChildFragmentManager(), Four_Fragment.class.getSimpleName());
                new F4_fragment().show(getChildFragmentManager(), F4_fragment.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Five_Fragment().show(getChildFragmentManager(), Five_Fragment.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Six_Fragment().show(getChildFragmentManager(), Six_Fragment.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Senven_Fragment().show(getChildFragmentManager(), Senven_Fragment.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Eight_Fragment().show(getChildFragmentManager(), Eight_Fragment.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Night_Fragment().show(getChildFragmentManager(), Night_Fragment.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Ten_Fragment().show(getChildFragmentManager(),Ten_Fragment.class.getSimpleName());
            }
        });
    }

    @Override
    protected void initData() {
        binding.test6FragmentTv.setText(getArguments().getString(Test_Fragment2.class.getSimpleName(), "没有想出名字哟"));
    }
}
