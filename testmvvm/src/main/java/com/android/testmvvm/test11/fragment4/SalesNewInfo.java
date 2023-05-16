package com.android.testmvvm.test11.fragment4;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.android.testmvvm.BR;

public class SalesNewInfo extends BaseObservable {
    private String title;
    private Boolean isBegging;

    @Bindable
    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }
    @Bindable
    public Boolean getBegging() {
        return isBegging;
    }



    public void setBegging(Boolean begging) {
        isBegging = begging;
        notifyPropertyChanged(BR.begging);
    }
}