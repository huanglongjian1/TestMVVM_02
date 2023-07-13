package com.android.test2mvvm.test5.fragment5.paging;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class UserViewModel extends ViewModel {

    public LiveData<PagedList<UserInfo>> userPagedList;
    DataSource<Integer, UserInfo> dataSource;

    public UserViewModel() {
        PagedList.Config config = new PagedList.Config.Builder().
                // 用于控件占位
                        setEnablePlaceholders(true).
                // 设置每页的大小
                        setPageSize(PositionalUserDataSource.PAGE_SIZE).
                // 设置当距离底部还有多少条数据时开始加载下一页
                        setPrefetchDistance(3).
                // 设置首次加载数据的数量  默认为 page_size 的三倍
                        setInitialLoadSizeHint(PositionalUserDataSource.PAGE_SIZE * 3).
                // 设置pagedList 所能承受的最大数量
                        setMaxSize(65536 * PositionalUserDataSource.PAGE_SIZE).
                build();
        UserDataSourceFactory dataSourceFactory = new UserDataSourceFactory();
        dataSource=dataSourceFactory.create();
        userPagedList = new LivePagedListBuilder<>(dataSourceFactory, config).build();
    }
    public void invalidateDataSource() {
        dataSource.invalidate();
    }
    public LiveData<PagedList<UserInfo>> getUserPagedList() {
         return userPagedList;
    }
}

