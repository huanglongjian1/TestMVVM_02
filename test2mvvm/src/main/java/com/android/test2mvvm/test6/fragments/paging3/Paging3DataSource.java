package com.android.test2mvvm.test6.fragments.paging3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingSource;

import com.android.test2mvvm.test6.fragments.paging3.bean.DatasBean;

import java.util.ArrayList;
import java.util.List;

import kotlin.coroutines.Continuation;

public class Paging3DataSource extends PagingSource<Integer, DatasBean> {


    @Nullable
    @Override
    public Object load(@NonNull LoadParams<Integer> loadParams, @NonNull Continuation<? super LoadResult<Integer, DatasBean>> continuation) {
        int page = 0;
        if(loadParams.getKey()!=null){
            page=loadParams.getKey();
        }

        List<DatasBean> datas = new ArrayList<>();
        for (int i = 0+10*page; i < 10+10*page; i++) {
            DatasBean datasBean = new DatasBean();
            datasBean.setDesc("A" + i);
            datasBean.setPublishTime(System.currentTimeMillis());
            datasBean.setType(1);
            datasBean.setAuthor("aa" + i);
            datas.add(datasBean);
        }
        int nextKey= page+1;


        return new LoadResult.Page<>(datas, null, nextKey);
    }

}