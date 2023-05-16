package com.android.testmvvm.test9;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.testmvvm.R;
import com.android.testmvvm.databinding.Test9ActivityLayoutBinding;
import com.android.testmvvm.test9.hilt.HttpObject;
import com.android.testmvvm.uitl.Constants;
import com.android.testmvvm.uitl.Loge;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
@Route(path = Constants.TEST9_ACTIVITY)
public class Test9_Activity extends AppCompatActivity {
    private MyViewModel viewModel;
    private Test9ActivityLayoutBinding bindingBinding;

    @Inject
    HttpObject httpObject;

    @Inject
    HttpObject httpObject2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingBinding = DataBindingUtil.setContentView(this, R.layout.test9_activity_layout);
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        bindingBinding.setData(viewModel);//.setXxx(yyy)。Xxx表示在布局文件data标签的name
        bindingBinding.setLifecycleOwner(this);//如果没有这行，数据的改变不会被观察到
        bindingBinding.btnTest9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loge.e(httpObject.hashCode() + "-");
                Loge.e(httpObject2.hashCode() + "-");
                Loge.e(httpObject.getS());
                httpObject.setS("AAAAA");
                Loge.e(httpObject.getS());
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.save();
    }

}
