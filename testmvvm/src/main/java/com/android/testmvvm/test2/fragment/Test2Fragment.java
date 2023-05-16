package com.android.testmvvm.test2.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProvider;

import com.android.testmvvm.R;
import com.android.testmvvm.databinding.Test2FragmentLayoutBinding;
import com.android.testmvvm.uitl.Loge;

public class Test2Fragment extends Fragment {
    private TestBindLiveDataViewModel mViewModel;
    private Test2FragmentLayoutBinding mDataBinding;
    private TestBindLiveBean mLiveBean;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.test2_fragment_layout, container, false);
        return mDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TestBindLiveDataViewModel.class);
        mDataBinding.setViewModel(mViewModel);
        mDataBinding.setLifecycleOwner(getViewLifecycleOwner());
        //this.getLifecycle().addObserver( mViewModel);
        mDataBinding.button1.setOnClickListener(new View.OnClickListener() {
            int i = 0;

            @Override
            public void onClick(View v) {
                mViewModel.liveData.setValue(new TestBindLiveBean("李四", "" + (i++)));
            }
        });

        mDataBinding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e("onClick");
                mDataBinding.unbind();
            }
        });


    }

    @Override
    public void onDestroyView() {
        Log.d("TAG", "onDestroyView");
        mDataBinding.unbind();
        super.onDestroyView();
    }
}
