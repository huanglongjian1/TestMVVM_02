package com.android.test2mvvm.test5.fragment5.book;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import com.android.test2mvvm.util.Loge;

import java.util.ArrayList;
import java.util.List;

class BookDataSource extends PositionalDataSource<Book> {
    /**
     * 加载初始化数据，可以这么来理解，加载的是第一页的数据。
     * 形象的说，当我们第一次打开页面，需要回调此方法来获取数据。
     */
    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull final LoadInitialCallback<Book> callback) {
        callback.onResult(fetchItems(0, 5), 0, 100);
    }

    /**
     * 当有了初始化数据之后，滑动的时候如果需要加载数据的话，会调用此方法。
     */
    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull final LoadRangeCallback<Book> callback) {
        Loge.e(params.startPosition+"----"+params.loadSize);
        callback.onResult(fetchItems(params.startPosition, params.loadSize));
    }

    private List<Book> fetchItems(int startPosition, int pageSize) {
        List<Book> list = new ArrayList<>();
        for (int i = startPosition; i < startPosition + pageSize; i++) {
            Book book = new Book();
            book.setTitle(Constants_book.title + i);
            book.setAuthor(Constants_book.author + i);
            book.setContent(Constants_book.content + i);
            book.setImg(Constants_book.imgUrl + i);
            list.add(book);
        }
        return list;
    }
}