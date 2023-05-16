package com.android.testmvvm.test2.fragment;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.android.testmvvm.BR;


public class TestBindLiveBean extends BaseObservable {
    private String user;
    private String agee;

    public TestBindLiveBean(String name, String a) {
        user = name;
        agee = a;
    }

    @Bindable
    public String getAgee() {
        return agee;
    }

    public void setAgee(String agee) {
        this.agee = agee;
        notifyPropertyChanged(BR.agee);
    }

    public void setUser(String user) {
        this.user = user;
        notifyPropertyChanged(BR.user);

    }

    public String getUser() {
        return user;
    }
}
