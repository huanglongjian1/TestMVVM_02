package com.android.test2mvvm.test5.fragment3;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;

import com.android.test2mvvm.base.BaseViewModel;

public class Fragment3_ViewModel extends BaseViewModel {
    public ObservableArrayList<Goods> observableArrayList = new ObservableArrayList<>();

    public Fragment3_ViewModel(@NonNull Application application) {
        super(application);
         init_data();
    }

    private void init_data() {
        for (int i = 0; i < 5; i++) {
            Goods goods = new Goods("点赞" + i, "描述" + i);
            observableArrayList.add(goods);
        }
    }
}
