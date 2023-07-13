package com.android.test2mvvm.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class Base_new_LazyFragment extends Fragment {

    private boolean isOk = false; // 是否完成View初始化
    private boolean isFirst = true; // 是否为第一次加载
    protected View root_view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root_view = getMyView(inflater, container, savedInstanceState); // 获取子fragment的view
        initView();
        isOk = true; // 完成initView后改变view的初始化状态为完成
        return root_view;
    }

    // 子fragment初始化view的方法
    protected abstract void initView();

    // 获取子fragment的view
    protected abstract View getMyView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onResume() {
        super.onResume();
        initLoadData(); // 在onResume中进行数据懒加载
    }

    private void initLoadData() {
        if (isOk && isFirst) { // 加载数据时判断是否完成view的初始化，以及是不是第一次加载此数据
            loadData();
            isFirst = false; // 加载第一次数据后改变状态，后续不再重复加载
        }
    }

    // 子fragment实现懒加载的方法
    protected abstract void loadData();


}
