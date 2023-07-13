package com.android.test2mvvm.test5.bean;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableField;

public class Teacher {
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<Integer> age = new ObservableField<>();
    public final ObservableArrayList<String> observableArrayList = new ObservableArrayList<>();
    public final ObservableArrayMap<String, String> observableArrayMap = new ObservableArrayMap<>();
}
