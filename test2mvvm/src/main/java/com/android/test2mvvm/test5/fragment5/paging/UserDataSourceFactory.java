package com.android.test2mvvm.test5.fragment5.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class UserDataSourceFactory extends DataSource.Factory<Integer, UserInfo> {

    // 这里可以根据需求换成另外两种DataSource即可。
    private MutableLiveData<PositionalUserDataSource> liveDataSource = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource<Integer, UserInfo> create() {
        PositionalUserDataSource source = new PositionalUserDataSource();
        liveDataSource.postValue(source);
        return source;
    }

}

