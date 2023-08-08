package com.android.test2mvvm.test6.test_fragment3;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestFragment02Binding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test6.test_fragment2.Test_Fragment2;
import com.android.test2mvvm.test6.test_fragment3.eight.Eight_Fragment;
import com.android.test2mvvm.test6.test_fragment3.eleven.Eleven_Fragment;
import com.android.test2mvvm.test6.test_fragment3.five.Five_Fragment;
import com.android.test2mvvm.test6.test_fragment3.four.Four_Fragment;
import com.android.test2mvvm.test6.test_fragment3.night.Night_fragment;
import com.android.test2mvvm.test6.test_fragment3.one.One_Fragment;
import com.android.test2mvvm.test6.test_fragment3.senven.Senven_Fragment;
import com.android.test2mvvm.test6.test_fragment3.six.Six_fragment;
import com.android.test2mvvm.test6.test_fragment3.ten.Ten_Fragment;
import com.android.test2mvvm.test6.test_fragment3.three.Three_Fragment;
import com.android.test2mvvm.test6.test_fragment3.two.Two_Fragment;

public class Test_Fragment3 extends BaseFragment<TestFragment02Binding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_fragment_02;
    }

    public static Fragment newInstance(String name) {
        Bundle bundle = new Bundle();
        bundle.putString(Test_Fragment3.class.getSimpleName(), name);
        Fragment fragment = new Test_Fragment3();
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
                new Four_Fragment().show(getChildFragmentManager(), Four_Fragment.class.getSimpleName());
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
                new Six_fragment().show(getChildFragmentManager(), Six_fragment.class.getSimpleName());
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
                new Night_fragment().show(getChildFragmentManager(), Night_fragment.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Ten_Fragment().show(getChildFragmentManager(),Ten_Fragment.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Eleven_Fragment().show(getChildFragmentManager(),Eleven_Fragment.class.getSimpleName());
            }
        });
    }

    @Override
    protected void initData() {

    }
}
