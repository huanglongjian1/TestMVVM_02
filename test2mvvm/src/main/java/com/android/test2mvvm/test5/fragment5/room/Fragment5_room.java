package com.android.test2mvvm.test5.fragment5.room;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test2mvvm.R;
import com.android.test2mvvm.test2.bean.huochepiao_12306.DataBean;
import com.android.test2mvvm.util.ExecutorUtil;
import com.android.test2mvvm.util.Loge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Fragment5_room extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ArticlesViewModel articlesViewModel = new ViewModelProvider(getActivity()).get(ArticlesViewModel.class);
        View view = inflater.inflate(R.layout.test5_fragment5, null);
        RecyclerView recyclerView = view.findViewById(R.id.test5_fragment5_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        MyAdapter myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        articlesViewModel.refresh(getActivity());
        articlesViewModel.articlePagedList.observe(getViewLifecycleOwner(), new Observer<PagedList<Article>>() {
            @Override
            public void onChanged(PagedList<Article> articles) {
                myAdapter.submitList(articles);
                Loge.e(articles.toString());
            }
        });
        TextView textView = view.findViewById(R.id.test5_fragment5_tv);
        final int[] index = {0};
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorUtil.execute(new Runnable() {
                    @Override
                    public void run() {
                        List<Article> list = new ArrayList<>();
                        for (int i = index[0]; i < index[0] * 20 + 20; i++) {
                            //  int i1 = new Random().nextInt(Integer.MAX_VALUE);
                            Article articlea = new Article(i, "author" + i, "desc" + i, "title" + i);
                            list.add(articlea);
                        }
                        insertArticles(list);
                        index[0]++;
                    }
                });
//                String s = ExecutorUtil.submit(new Callable<String>() {
//                    @Override
//                    public String call() throws Exception {
//                        return Thread.currentThread().getName();
//                    }
//                });
//                Loge.e(s);
            }
        });

        return view;
    }

    private class BackgroundThreadTask implements Executor {
        public BackgroundThreadTask() {
            this.execute(new Runnable() {
                @Override
                public void run() {
                    Log.d("BackgroundThreadTask", "run");
                }
            });
        }

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }

    /**
     * 插入数据
     */
    private void insertArticles(final List<Article> articles) {
        Observable.timer(0, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Throwable {
                        ArticleDatabase.getInstance(getActivity()).articleDao().insertArticles(articles);
                    }
                });
    }

}
