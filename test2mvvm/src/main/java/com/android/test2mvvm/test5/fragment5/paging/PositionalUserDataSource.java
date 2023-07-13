package com.android.test2mvvm.test5.fragment5.paging;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import com.android.test2mvvm.util.Loge;

import java.util.ArrayList;
import java.util.List;

public class PositionalUserDataSource extends PositionalDataSource<UserInfo> {

    public static final int PAGE_SIZE =10;

    /**
     * 首次加载数据
     */
    @Override
    public void loadInitial(@NonNull LoadInitialParams params,
                            @NonNull final LoadInitialCallback<UserInfo> callback) {


        callback.onResult(getData(0, PAGE_SIZE), 0, 200);

    }

    /**
     * 第 N 页加载数据
     *
     * @param params
     * @param callback
     */
    @Override
    public void loadRange(@NonNull LoadRangeParams params,
                          @NonNull final LoadRangeCallback<UserInfo> callback) {

        Loge.e(params.startPosition + "----" + params.loadSize);
        callback.onResult(getData(params.startPosition, params.loadSize));

    }

    private List<UserInfo> getData(int start, int count) {
        List<UserInfo> list = new ArrayList<>();
        Loge.e(start + "-----" + count);
        for (int i = start; i < count; i++) {
            UserInfo userInfo = new UserInfo(i, "userName" + i, "userAvatar" + i);
            list.add(userInfo);
        }
        return list;
    }
}


