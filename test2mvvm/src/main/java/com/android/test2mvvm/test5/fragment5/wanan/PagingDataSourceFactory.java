package com.android.test2mvvm.test5.fragment5.wanan;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.android.test2mvvm.test5.fragment5.wanan.api.bean.DatasBean;

public class PagingDataSourceFactory extends DataSource.Factory<String, DatasBean> {

    @NonNull
    @Override
    public DataSource<String, DatasBean> create() {
        PagingDataSource dataSource = new PagingDataSource();
        return dataSource;
    }
}