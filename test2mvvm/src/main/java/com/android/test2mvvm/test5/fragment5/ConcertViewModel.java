package com.android.test2mvvm.test5.fragment5;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class ConcertViewModel extends ViewModel {

    private final LiveData<PagedList<Concert>> convertList;
    private DataSource<Integer, Concert> concertDataSource;

    public ConcertViewModel() {
        ConcertFactory concertFactory = new ConcertFactory();
        concertDataSource = concertFactory.create();
        convertList = new LivePagedListBuilder<>(concertFactory, 20).build();
    }

    public void invalidateDataSource() {
        concertDataSource.invalidate();
    }

    public LiveData<PagedList<Concert>> getConvertList() {
        return convertList;
    }
}
