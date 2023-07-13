package com.android.test2mvvm.test5.fragment5.book;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class BookFactory extends DataSource.Factory<Integer, Book> {
    private MutableLiveData<BookDataSource> mSourceLiveData =
            new MutableLiveData<>();

    @Override
    public DataSource<Integer, Book> create() {
        BookDataSource concertDataSource = new BookDataSource();
        mSourceLiveData.postValue(concertDataSource);
        return concertDataSource;
    }

}