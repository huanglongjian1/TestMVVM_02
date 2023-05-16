package com.android.testmvvm.test10;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private MutableLiveData<Integer> number;
    public MutableLiveData<Integer> getNumber(){
        if(this.number == null){
            this.number = new MutableLiveData<>();
            this.number.setValue(0);
        }
        return this.number;
    }
    public void add(int x){
        this.number.setValue(this.number.getValue()+x);
        if(this.number.getValue() < 0){
            this.number.setValue(0);
        }
    }
}

