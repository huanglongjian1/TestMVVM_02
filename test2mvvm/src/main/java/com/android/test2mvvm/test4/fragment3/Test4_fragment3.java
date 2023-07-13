package com.android.test2mvvm.test4.fragment3;

import android.view.View;

import androidx.room.Room;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.Test4Fragment3Binding;
import com.android.test2mvvm.test4.base.BaseFragment;
import com.android.test2mvvm.test4.fragment3.dao.UserDatabase;
import com.android.test2mvvm.test4.fragment3.dao.User_bean;
import com.android.test2mvvm.test4.fragment3.dao.User_bean_Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Test4_fragment3 extends BaseFragment<Test4Fragment3Binding> {
    UserDatabase userDatabase;
    User_bean_Dao dao;
    List<User_bean> user_beanList;

    @Override
    protected int getLayoutId() {
        return R.layout.test4_fragment3;
    }

    public void init_data() {
        user_beanList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User_bean user_bean = new User_bean(i, "hlj", "ABCD");
            user_beanList.add(user_bean);
        }
    }

    @Override
    protected void initView() {
        init_data();
        userDatabase = Room.databaseBuilder(getContext(), UserDatabase.class, "user_bean.db").build();
        dao = userDatabase.user_bean_dao();
        binding.test4Fragment3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.timer(0, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Throwable {
                                dao.insert_List(user_beanList);
                            }
                        });

            }
        });
        binding.test4Fragment3Tv.setOnClickListener(new View.OnClickListener() {
            int i = 0;

            @Override
            public void onClick(View v) {
                Observable.timer(0, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Throwable {
                                binding.test4Fragment3Tv.setText(dao.getAll().size() + "-" + i++);
                            }
                        });
            }
        });
        binding.test4Fragment3DialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void initData() {

    }
}
