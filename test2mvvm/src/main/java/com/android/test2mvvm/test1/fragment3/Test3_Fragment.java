package com.android.test2mvvm.test1.fragment3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.test2mvvm.BR;
import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test3_FragmentBing;
import com.android.test2mvvm.util.Loge;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Test3_Fragment extends Fragment {
    private Test3_FragmentBing mBinding;
    public static final int VISIBLE = View.VISIBLE;
    public static final int GONE = View.GONE;
    public MainViewModel mainViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.test1_fragment3, container, false);
        mBinding.setLifecycleOwner(this);
        mBinding.setView(this);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mBinding.setModel(mainViewModel);
        mainViewModel.showLoading.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean showLoading) {
                Loge.e(String.valueOf(showLoading));
                mBinding.loading.setVisibility(showLoading ? View.VISIBLE : View.GONE);
                mBinding.btnSave.setEnabled(!showLoading);
            }
        });
        mainViewModel.getHttpResultMutableLiveData().observe(getViewLifecycleOwner(), new Observer<HttpResult<String>>() {
            @Override
            public void onChanged(HttpResult<String> stringHttpResult) {
                Loge.e(stringHttpResult.status + "\n" + stringHttpResult.data + "\n" + stringHttpResult.msg);
            }
        });
        HttpResult<String> httpResult = new HttpResult<>();
        httpResult.msg = "success";
        httpResult.status = 200;
        httpResult.data = "今晚来吃鸡";
        mBinding.test1NowTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainViewModel.getHttpResultMutableLiveData().setValue(httpResult);

            }
        });
        mBinding.seeAds.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (isChecked){
                   mBinding.etContent.setVisibility(View.GONE);
               }else {
                   mBinding.etContent.setVisibility(View.VISIBLE);
               }

            }
        });

        return mBinding.getRoot();
    }

    public void onSaveClick(View view) {
        Toast.makeText(getActivity(), "点击了 保存 按钮", Toast.LENGTH_SHORT).show();
        mainViewModel.setData();
    }

    public void onLoadClick(View view) {
        Toast.makeText(getActivity(), "点击了 加载 按钮", Toast.LENGTH_SHORT).show();
        mainViewModel.getData();
    }
}
