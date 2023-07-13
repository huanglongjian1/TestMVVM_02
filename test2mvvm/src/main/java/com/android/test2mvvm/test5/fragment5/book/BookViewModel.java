package com.android.test2mvvm.test5.fragment5.book;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.android.test2mvvm.util.Loge;

public class BookViewModel extends ViewModel {
    private final LiveData<PagedList<Book>> convertList;
    private DataSource<Integer, Book> concertDataSource;

    public BookViewModel() {
        BookFactory bookFactory = new BookFactory();
        concertDataSource = bookFactory.create();
        convertList = new LivePagedListBuilder<>(bookFactory, 5).build();
    }

    public void invalidateDataSource() {
        Loge.e("刷新");
        concertDataSource.invalidate();
    }

    public LiveData<PagedList<Book>> getConvertList() {
        return convertList;
    }

}