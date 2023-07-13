package com.android.test2mvvm.test4.fragment2;


import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

public class Test_bean {
    ObservableList<String> observableList=new ObservableArrayList();


    public ObservableList getObservableList() {
        return observableList;
    }

    public void setObservableList(ObservableList observableList) {
        this.observableList = observableList;

    }
}
