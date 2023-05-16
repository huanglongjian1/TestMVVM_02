package com.android.testmvvm.test11.fragment;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.android.testmvvm.databinding.Test11FragmentLayoutBinding;
import com.android.testmvvm.databinding.Test1FragmentLayout1Binding;

import java.util.ArrayList;
import java.util.List;

public class MainVM extends AndroidViewModel {
    @SuppressLint("StaticFieldLeak")
    private static Test11FragmentLayoutBinding binding;
    @SuppressLint("StaticFieldLeak")
    private static Test_Fragment mainActivity;
    //初始化好
    public final List<Bean> data = new ArrayList<>();

    public MainVM(@NonNull Application application) {
        super(application);
    }

    public void setBinding(Test11FragmentLayoutBinding binding, Test_Fragment mainActivity) {
        //把binding和mainActivity都赋值给MainVM作为静态变量备用，因为很多绑定的控件都只能用静态方法
        MainVM.binding = binding;
        MainVM.mainActivity = mainActivity;

        //设置适配器方式和以往不同
        binding.setAdp(new Adapter(mainActivity.getActivity(), data));
        //通过binding来设置点击长按事件
        binding.test11FragmentListview.setOnItemClickListener(null);
        binding.test11FragmentListview.setOnItemLongClickListener(null);
        //往列表里添加数据
        for (int i=0;i<10;i++){
            data.add(new Bean("emmmmm"));
        }
        binding.test11TvSize.setText(data.size()+"-");
        //更新列表
        binding.getAdp().notifyDataSetChanged();
        //不在主线陈更新
        //     mainActivity.runOnUiThread(() -> binding.getAdp().notifyDataSetChanged());
    }
}
