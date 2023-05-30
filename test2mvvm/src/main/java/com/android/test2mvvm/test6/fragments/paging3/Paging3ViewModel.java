package com.android.test2mvvm.test6.fragments.paging3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import com.android.test2mvvm.test6.fragments.paging3.bean.DatasBean;

import kotlinx.coroutines.CoroutineScope;

public class Paging3ViewModel extends ViewModel {

    PagingConfig pagingConfig = new PagingConfig(20, 3);

    public LiveData<PagingData<DatasBean>> getArticleData() {
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
       // Pager<Integer, DatasBean> pager = new Pager<>(pagingConfig, () -> new Paging3DataSource());
         Pager<Integer, DatasBean> pager = new Pager<>(pagingConfig,() -> new Paging3DataSource_03());

        LiveData<PagingData<DatasBean>> cachedResult = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);
        return cachedResult;
    }

}