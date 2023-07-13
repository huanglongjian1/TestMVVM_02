package com.android.test2mvvm.test5.fragment4;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;

import com.android.test2mvvm.base.BaseViewModel;
import com.android.test2mvvm.test5.fragment4.bean.Person;

public class Fragment4_ViewModel extends BaseViewModel {
    public ObservableArrayList<Person> personObservableArrayList = new ObservableArrayList<>();

    public Fragment4_ViewModel(@NonNull Application application) {
        super(application);
        init_data();
    }

    public void init_data() {
        for (int i = 0; i < 20; i++) {
            Person person = new Person("aaaa" + i, String.valueOf(i));
            personObservableArrayList.add(person);
        }
    }
}
