package com.android.testmvvm.test2.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class TestBindLiveDataViewModel extends ViewModel {
    private final static String TAG = "TestBindLiveDataViewModel";
    public MutableLiveData<TestBindLiveBean> liveData= new MutableLiveData<>();

    public TestBindLiveDataViewModel(){

    }

    public LiveData<TestBindLiveBean> getData(){
        return liveData;
    }

    public void setData(TestBindLiveBean value){
        liveData.setValue(value);
    }

    @Override
    public void onCleared(){
        super.onCleared();
    }
}
