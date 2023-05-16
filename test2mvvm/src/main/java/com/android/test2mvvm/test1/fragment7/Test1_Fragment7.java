package com.android.test2mvvm.test1.fragment7;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test1Fragment7Binding;


public class Test1_Fragment7 extends Fragment {
    private Test1Fragment7Binding binding;
    private ImageUrlViewModel mViewModel;
    private ProgressDialog mDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.test1_fragment7, container, false);

        // 建立ViewModel，并将Activity的生命周期绑定到ViewModel上
        mViewModel = new ViewModelProvider(getActivity()).get(ImageUrlViewModel.class);

        // 为ViewModel的UrlData建立数据监听，并监听数据变化，根据数据更新UI
        mViewModel.getImageUrl().observe(getViewLifecycleOwner(), new Observer<UrlData<ImageUrlBean.UrlBean>>() {
            @Override
            public void onChanged(@Nullable UrlData<ImageUrlBean.UrlBean> data) {
                mDialog.dismiss();
                if (data.getErrorMsg() != null) {
                    Toast.makeText(getActivity(), data.getErrorMsg(),
                            Toast.LENGTH_LONG).show();
                }
                // 监听到数据变化后，通过databinding更改布局UI，若未使用databinding，则需要自己写相关UI更新逻辑
                binding.setImage(data.getData());
            }
        });

        // 监听特殊状态下更新UI的操作
        mViewModel.getState().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                mDialog.dismiss();
                if (integer == ImageUrlViewModel.STATE_NO_PRE_IMAGE) {
                    Toast.makeText(getActivity(), "没有前一张图片了！",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        binding.setClicker(new Clicker());

        mDialog = new ProgressDialog(getActivity());
        mDialog.setTitle("加载中");
        mDialog.show();
        mViewModel.loadImage();

        return binding.getRoot();
    }

    public class Clicker {

        public void onClick(View view) {
            mDialog.show();
//            switch (view.getId()) {
//                case R.id.btn_pre:
//                    mViewModel.loadPreImage()
//                    ;
//                    break;
//                case 2:
//                    // R.id.btn:
//                    mViewModel.loadImage()
//                    ;
//                    break;
//                case 3:
//                    // R.id.btn_next:
//                    mViewModel.loadNextImage()
//                    ;
//                    break;
//                default:
//                    break;
            if (view.getId() == R.id.btn_pre) {
                mViewModel.loadPreImage();
            } else if (view.getId() == R.id.btn) {
                mViewModel.loadImage();
            } else if (view.getId() == R.id.btn_next) {
                mViewModel.loadNextImage();
            }
        }
    }
}
