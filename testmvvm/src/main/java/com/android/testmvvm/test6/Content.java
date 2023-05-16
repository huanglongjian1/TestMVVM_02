package com.android.testmvvm.test6;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.android.testmvvm.BR;

public class Content extends BaseObservable {
    private String title;
    private String subTitle;

    public Content(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    @Bindable
    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        notifyPropertyChanged(BR.subTitle);
    }

    @Bindable public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }
}
