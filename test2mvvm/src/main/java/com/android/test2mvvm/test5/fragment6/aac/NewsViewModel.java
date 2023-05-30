package com.android.test2mvvm.test5.fragment6.aac;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class NewsViewModel extends AndroidViewModel {

    private MutableLiveData<NewsDataVo> mData;

    private LiveData<NewsDataVo> switchDataMap;

    LiveData<NewsDataVo> getSwitchDataMap() {
        return switchDataMap;
    }

    public NewsViewModel(@NonNull Application application) {
        super(application);
        mData = new MutableLiveData<>();
        switchDataMap = Transformations.switchMap(mData, new Function<NewsDataVo, LiveData<NewsDataVo>>() {
            @Override
            public LiveData<NewsDataVo> apply(NewsDataVo input) {
                MutableLiveData<NewsDataVo> temp = new MutableLiveData<>();
                temp.setValue(input);
                return temp;
            }
        });

    }


    /**
     * 模拟获取网络数据
     */
    void httpGetData() {
        int len = 10;
        for (int i = 0; i < len; i++) {
            NewsDataVo dataVo = new NewsDataVo();
            dataVo.setId("1223" + i);
            dataVo.setNewsTitle("Android AccArchitecture框架简介" + i);
            dataVo.setNewsContent("Android Architecture Components,简称 AAC,一个处理UI的生命周期与数据的持久化的架构" + i);
            dataVo.setReadNum(i);
            mData.setValue(dataVo);
        }
    }
}