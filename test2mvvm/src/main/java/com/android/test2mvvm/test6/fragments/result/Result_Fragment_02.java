package com.android.test2mvvm.test6.fragments.result;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.ResultFragmentBinding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test6.fragments.result.fragmenta.Result_FragmentA_02;
import com.android.test2mvvm.test6.fragments.result.fragmentb.Result_FragmentB_02;

public class Result_Fragment_02 extends BaseFragment<ResultFragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.result_fragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        getChildFragmentManager().beginTransaction().replace(R.id.result_fragmentA, new Result_FragmentA_02()).commit();
        getChildFragmentManager().beginTransaction().replace(R.id.result_fragmentB, new Result_FragmentB_02()).commit();
    }
}
