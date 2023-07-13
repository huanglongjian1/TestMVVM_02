package com.android.test2mvvm.test5.fragment5.room;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ArticlesViewModel extends AndroidViewModel {
    public static final int PER_PAGE = 20;
    LiveData<PagedList<Article>> articlePagedList;

    public ArticlesViewModel(Application application) {
        super(application);
        ArticleDatabase articleDatabase = ArticleDatabase.getInstance(application);

        articlePagedList = (new LivePagedListBuilder<>(articleDatabase.articleDao().getArticlesList(), ArticlesViewModel.PER_PAGE))
                .setBoundaryCallback(new ArticleBoundaryCallback(application))
                .build();
    }

    public void refresh(Context context) {

        Observable.timer(0, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io()).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Throwable {
                        ArticleDatabase.getInstance(context).articleDao().clear();
                    }
                });

    }

}
