package com.android.test2mvvm.test6.fragments.result;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.android.test2mvvm.R;
import com.android.test2mvvm.Test2_App;
import com.android.test2mvvm.databinding.ResultFragmentBinding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test6.fragments.result.fragmenta.Result_FragmentA;
import com.android.test2mvvm.test6.fragments.result.fragmentb.Result_FragmentB;
import com.android.test2mvvm.test6.fragments.seekbar_fragment.SeekBar_Fragment;
import com.android.test2mvvm.test6.fragments.seekbar_fragment.SeekBar_ViewModel;
import com.android.test2mvvm.util.Loge;

import java.util.concurrent.ExecutionException;

public class Result_Fragment extends BaseFragment<ResultFragmentBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.result_fragment;
    }

    @Override
    protected void initView() {
        binding.resultFragmentTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e("发信息");
                Bundle bundle = new Bundle();
                bundle.putString("child", "吃饭了吗?");
                getChildFragmentManager().setFragmentResult("bbb", bundle);
            }
        });

        getChildFragmentManager().beginTransaction().replace(R.id.result_fragmentA, new Result_FragmentA()).commit();
        getChildFragmentManager().beginTransaction().replace(R.id.result_fragmentB, new Result_FragmentB()).commit();


    }

    @Override
    public void onResume() {
        super.onResume();
        Loge.e(Result_FragmentB.class.getSimpleName());
        getChildFragmentManager().setFragmentResultListener("b", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Loge.e("=================");
                Loge.e(result.getString("btv", "没取到数据"));
            }
        });
        getParentFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentResumed(@NonNull FragmentManager fm, @NonNull Fragment f) {
                super.onFragmentResumed(fm, f);
                Loge.e("onFragmentResumed+:- " + fm.toString() + " -:- " + f.toString());
            }
        }, true);
    }

    @Override
    protected void initData() {
        SeekBar_ViewModel seekBar_viewModel = new ViewModelProvider(getActivity()).get(SeekBar_ViewModel.class);
        binding.setSeekbarviewmodel(seekBar_viewModel);
        Loge.e(seekBar_viewModel.toString()+"======");

        SeekBar_ViewModel seekBar_viewModel1 = new ViewModelProvider(this).get(SeekBar_ViewModel.class);
        binding.setSeekbarviewmodel(seekBar_viewModel1);
        Loge.e(seekBar_viewModel1.toString());

    }
}
