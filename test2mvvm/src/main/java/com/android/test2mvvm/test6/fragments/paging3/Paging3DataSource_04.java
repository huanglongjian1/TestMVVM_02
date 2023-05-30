package com.android.test2mvvm.test6.fragments.paging3;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingSource;

import com.android.test2mvvm.test6.fragments.paging3.bean.DatasBean;

import java.util.concurrent.CountDownLatch;

import io.reactivex.rxjava3.core.Observable;
import kotlin.coroutines.Continuation;

public class Paging3DataSource_04 extends PagingSource<Integer, DatasBean> {

    @Nullable
    @Override
    public Object load(@NonNull LoadParams<Integer> loadParams, @NonNull Continuation<? super LoadResult<Integer, DatasBean>> continuation) {



        return null;
    }
}
