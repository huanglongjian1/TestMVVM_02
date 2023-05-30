package com.android.test2mvvm.test6.fragments.seekbar_fragment;


import android.widget.SeekBar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.SeekbarFragmentBinding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test6.fragments.NavHost;
import com.android.test2mvvm.util.Loge;

public class SeekBar_Fragment extends BaseFragment<SeekbarFragmentBinding> implements NavHost {
    @Override
    protected int getLayoutId() {
        return R.layout.seekbar_fragment;
    }

    @Override
    protected void initView() {
        binding.seekbarTest6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar_viewModel.getIntegerMutableLiveData().setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    SeekBar_ViewModel seekBar_viewModel;

    @Override
    protected void initData() {
        seekBar_viewModel = new ViewModelProvider(getActivity()).get(SeekBar_ViewModel.class);
        binding.setViewmodel(seekBar_viewModel);
        seekBar_viewModel.getIntegerMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Loge.e(seekBar_viewModel.toString());
            }
        });
        binding.setLifecycleOwner(this);
    }

    @Override
    public NavController getNavController() {
        return null;
    }
}
