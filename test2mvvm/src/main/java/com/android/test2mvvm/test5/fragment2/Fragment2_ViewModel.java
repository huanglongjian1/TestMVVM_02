package com.android.test2mvvm.test5.fragment2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;

import com.android.test2mvvm.base.BaseViewModel;

public class Fragment2_ViewModel extends BaseViewModel {
    public final ObservableArrayList<String> observableArrayList = new ObservableArrayList<>();

    public Fragment2_ViewModel(@NonNull Application application) {
        super(application);
    }
}
