package com.android.test2mvvm.test5.fragment5;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import com.android.test2mvvm.test5.fragment2.adapter.Bean;
import com.android.test2mvvm.util.Loge;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConcertDataSource extends PositionalDataSource<Concert> {

    private final Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl("http://api.apiopen.top/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private final Service mService;

    public ConcertDataSource() {
        mService = mRetrofit.create(Service.class);
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull final LoadInitialCallback<Concert> callback) {
        //        fetchItem(0, params.pageSize, new Callback() {
        //            @Override
        //            public void onResult(Bean value) {
        //                callback.onResult(value.getResult(), 0, 2000);
        //            }
        //        });

        callback.onResult(fetchItems(0, 20), 0, 500);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull final PositionalDataSource.LoadRangeCallback<Concert> callback) {
        //        fetchItem(params.startPosition, params.loadSize, new Callback() {
        //            @Override
        //            public void onResult(Bean value) {
        //                callback.onResult(value.getResult());
        //            }
        //        });
        Loge.e(params.startPosition+"-"+params.loadSize);
        callback.onResult(fetchItems(params.startPosition, params.loadSize));
    }

    private List<Concert> fetchItems(int startPosition, int pageSize) {

        List<Concert> list = new ArrayList<>();
        for (int i = startPosition; i < startPosition + pageSize; i++) {
            Concert concert = new Concert();
            concert.setAuthor("author = " + i);
            concert.setContent("content = " + i);
            concert.setTitle("title = " + i);
            list.add(concert);
        }
        return list;
    }

    private void fetchItem(final int startPosition, int size, final Callback callback) {
//        mService.getCall(startPosition, size).subscribe(new Observer<Bean>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Bean value) {
//                callback.onResult(value);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
    }

    private interface Callback {

        void onResult(Bean value);
    }
}
