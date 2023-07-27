package com.android.test2mvvm.test6.test_fragment;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.TestFragment02Binding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test6.test_fragment.test01.Fragment_01;
import com.android.test2mvvm.test6.test_fragment.test02.Fragment_02;
import com.android.test2mvvm.test6.test_fragment.test03.Fragment03;
import com.android.test2mvvm.test6.test_fragment.test04.Test04_Fragment;
import com.android.test2mvvm.test6.test_fragment.test05.Fragment_05;
import com.android.test2mvvm.test6.test_fragment.test06.Fragment_06;
import com.android.test2mvvm.test6.test_fragment.test07.Fragment_07;
import com.android.test2mvvm.test6.test_fragment.test08.Fragment_08;
import com.android.test2mvvm.test6.test_fragment.test09.Fragment_09;
import com.android.test2mvvm.test6.test_fragment.test10.Fragment10;
import com.android.test2mvvm.test6.test_fragment.test11.Fragment_11;
import com.android.test2mvvm.util.Loge;
import com.android.test2mvvm.util.SharedPreferencesUtil;
import com.tencent.mmkv.MMKV;

public class Test_Fragment_01 extends BaseFragment<TestFragment02Binding> {
    @Override
    protected int getLayoutId() {
        return R.layout.test_fragment_02;
    }

    public static Fragment newInstance(String name) {
        Bundle bundle = new Bundle();
        bundle.putString(Test_Fragment_01.class.getSimpleName(), name);
        Fragment fragment = new Test_Fragment_01();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView() {
        binding.test6FragmentTv.setText(getArguments().getString(this.getClass().getSimpleName(), "没有取到名字"));
        binding.test6FragmentBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_01 fragment_01 = new Fragment_01();
                fragment_01.show(getChildFragmentManager(), Fragment_01.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction().replace(R.id.test6_fragment, new Fragment_02()).commit();
            }
        });
        binding.test6FragmentBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MMKV mmkv = MMKV.defaultMMKV();
//                Loge.e(mmkv.getString("mmkv", "没有取到MMKV的数据"));
                Fragment03 fragment03 = new Fragment03();
                fragment03.show(getChildFragmentManager(), Fragment03.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Test04_Fragment test04_fragment = new Test04_Fragment();
                getChildFragmentManager().beginTransaction().add(R.id.test6_fragment, test04_fragment).commit();
            }
        });
        binding.test6FragmentBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_05 fragment_05 = new Fragment_05();
                fragment_05.show(getChildFragmentManager(), Fragment_05.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_06 fragment_06 = new Fragment_06();
                fragment_06.show(getChildFragmentManager(), Fragment_06.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_07 fragment_07 = new Fragment_07();
                fragment_07.show(getChildFragmentManager(), Fragment_07.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_08 fragment_08 = new Fragment_08();
                fragment_08.show(getChildFragmentManager(), Fragment_08.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_09 fragment_09 = new Fragment_09();
                fragment_09.show(getChildFragmentManager(), Fragment_09.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Fragment10().show(getChildFragmentManager(), Fragment10.class.getSimpleName());
            }
        });
        binding.test6FragmentBtn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Fragment_11().show(getChildFragmentManager(),Fragment_11.class.getSimpleName());
            }
        });
    }

    @Override
    protected void initData() {

    }
}
