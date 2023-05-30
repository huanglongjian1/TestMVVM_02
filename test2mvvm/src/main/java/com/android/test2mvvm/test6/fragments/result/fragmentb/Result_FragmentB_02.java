package com.android.test2mvvm.test6.fragments.result.fragmentb;

import android.widget.SeekBar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.test2mvvm.R;
import com.android.test2mvvm.Test2_App;
import com.android.test2mvvm.databinding.ResultFragmentBBinding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test6.fragments.result.ViewModel_AB;

public class Result_FragmentB_02 extends BaseFragment<ResultFragmentBBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.result_fragment_b;
    }

    @Override
    protected void initView() {
        binding.resultFragmentBSeekbar.setProgress(viewModel_ab.num.getValue());
        binding.resultFragmentBSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewModel_ab.num.setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    ViewModel_AB viewModel_ab;

    @Override
    protected void initData() {
        viewModel_ab = new ViewModelProvider(getActivity()).get(ViewModel_AB.class);
        binding.setDataab(viewModel_ab);
        binding.setLifecycleOwner(this);

        viewModel_ab.num.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

            }
        });

    }
}
