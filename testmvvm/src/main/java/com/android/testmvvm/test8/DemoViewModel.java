package com.android.testmvvm.test8;

import androidx.lifecycle.ViewModel;

public class DemoViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private DemoData mDemoData = new DemoData();

    public DemoData getDemoData() {
        return mDemoData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        //清除的方法,这个方法需要让你处理一些网络请求的停止或者数据加工的逻辑停止
    }
}