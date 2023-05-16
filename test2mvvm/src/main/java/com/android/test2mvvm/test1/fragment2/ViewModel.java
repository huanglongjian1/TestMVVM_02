package com.android.test2mvvm.test1.fragment2;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableBoolean;

public class ViewModel extends BaseObservable {
    public final ObservableBoolean isRefreshing = new ObservableBoolean();
}
