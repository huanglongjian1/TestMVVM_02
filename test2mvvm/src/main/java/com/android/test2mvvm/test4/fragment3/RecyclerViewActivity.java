package com.android.test2mvvm.test4.fragment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.test2mvvm.R;
import com.android.test2mvvm.test4.fragment3.dao.UserDatabase;
import com.android.test2mvvm.test4.fragment3.dao.User_bean;
import com.android.test2mvvm.test4.fragment3.dao.User_bean_Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @author zpn
 * @date 2020-09-24
 */
public class RecyclerViewActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView rv_recyclerview;
    private SwipeRefreshLayout sr_layout;
    private LinearLayoutManager mLayoutManager;

    private List<String> list;
    private MyAdapter adapter;

    private int lastVisibleItem = 0;
    public static final int PAGE_COUNT = 10;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        init_fragment();
        sr_layout = findViewById(R.id.sr_layout);
        rv_recyclerview = findViewById(R.id.rv_recyclerview);
        initData();
        initRefreshLayout();
        initRecyclerView();
    }

    private void init_fragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.test4_fragment3, new Test4_fragment3()).commit();
    }

    UserDatabase userDatabase;
    User_bean_Dao dao;

    private void initData() {
        list = new ArrayList<>();
        userDatabase = Room.databaseBuilder(this, UserDatabase.class, "user_bean.db").build();
        dao = userDatabase.user_bean_dao();
        Observable.create(new ObservableOnSubscribe<List<User_bean>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<User_bean>> emitter) throws Throwable {
                        List<User_bean> user_beanList = dao.getAll();
                        emitter.onNext(user_beanList);
                        emitter.onComplete();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User_bean>>() {
                    @Override
                    public void accept(List<User_bean> listA) throws Throwable {
                        for (User_bean user_bean : listA) {
                            list.add(user_bean.toString());
                        }
                        updateRecyclerView(0,PAGE_COUNT);
                    }
                });

    }

    private void initRefreshLayout() {
        //sr_layout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
        //      android.R.color.holo_orange_light, android.R.color.holo_green_light);
        sr_layout.setOnRefreshListener(this);
    }

    private void initRecyclerView() {
        adapter = new MyAdapter(getDatas(0, PAGE_COUNT), this, getDatas(0, PAGE_COUNT).size() > 0 ? true : false);
        mLayoutManager = new LinearLayoutManager(this);
        rv_recyclerview.setLayoutManager(mLayoutManager);
        rv_recyclerview.setAdapter(adapter);
        rv_recyclerview.setItemAnimator(new DefaultItemAnimator());

        rv_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (adapter.isFadeTips() == false && lastVisibleItem + 1 == adapter.getItemCount()) {
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                updateRecyclerView(adapter.getRealLastPosition(), adapter.getRealLastPosition() + PAGE_COUNT);
                            }
                        }, 500);
                    }

                    if (adapter.isFadeTips() == true && lastVisibleItem + 2 == adapter.getItemCount()) {
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                updateRecyclerView(adapter.getRealLastPosition(), adapter.getRealLastPosition() + PAGE_COUNT);
                            }
                        }, 500);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private List<String> getDatas(final int firstIndex, final int lastIndex) {
        List<String> resList = new ArrayList<>();
        for (int i = firstIndex; i < lastIndex; i++) {
            if (i < list.size()) {
                resList.add(list.get(i));
            }
        }
        return resList;
    }

    private void updateRecyclerView(int fromIndex, int toIndex) {
        List<String> newDatas = getDatas(fromIndex, toIndex);
        if (newDatas.size() > 0) {
            adapter.updateList(newDatas, true);
        } else {
            adapter.updateList(null, false);
        }
    }

    @Override
    public void onRefresh() {
        sr_layout.setRefreshing(true);
        adapter.resetDatas();
        updateRecyclerView(0, PAGE_COUNT);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sr_layout.setRefreshing(false);
            }
        }, 1000);
    }



}