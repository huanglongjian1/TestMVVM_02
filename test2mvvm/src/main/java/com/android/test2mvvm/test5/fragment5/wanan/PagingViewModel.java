package com.android.test2mvvm.test5.fragment5.wanan;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.android.test2mvvm.test5.fragment5.wanan.api.bean.DatasBean;

public class PagingViewModel extends ViewModel {

    private int pageSize = PagingDataSource.pagesize;
    //PagedList配置
    private PagedList.Config config = new PagedList.Config.Builder()
            .setInitialLoadSizeHint(pageSize)//设置首次加载的数量
            .setPageSize(pageSize)//设置每页加载的数量
            .setPrefetchDistance(2)//设置间隔每页最初数据项来时预加载下一页数据
            .setEnablePlaceholders(false)//设置是否启用UI占用符
            .build();

    //DataSource.Factory
    private DataSource.Factory<String, DatasBean> factory = new PagingDataSourceFactory();

    //LiveData
    private LiveData<PagedList<DatasBean>> mPagedList = new LivePagedListBuilder<>(factory, config)
            .build();

    public LiveData<PagedList<DatasBean>> getPagedList() {
        return mPagedList;
    }

}