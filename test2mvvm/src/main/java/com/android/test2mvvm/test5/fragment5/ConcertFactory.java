package com.android.test2mvvm.test5.fragment5;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class ConcertFactory extends DataSource.Factory<Integer, Concert> {

    private MutableLiveData<ConcertDataSource> mSourceLiveData =
            new MutableLiveData<>();

    @Override
    public DataSource<Integer, Concert> create() {
        ConcertDataSource concertDataSource = new ConcertDataSource();
        mSourceLiveData.postValue(concertDataSource);
        return concertDataSource;
    }
}
