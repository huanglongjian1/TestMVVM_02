package com.android.testmvvm.test7;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.android.testmvvm.BR;

import java.util.List;

public class Information extends BaseObservable {
    public String mName;
    public String mSex;
    public String mAge;
    public List<Information> informationList;
    public Information(){

    }
    public Information(String mName,String mSex,String mAge){
        this.mName = mName;
        this.mSex = mSex;
        this.mAge = mAge;
    }
    @Bindable
    public String getmAge() {
        return mAge;
    }
    @Bindable
    public String getmName() {
        return mName;

    }
    @Bindable
    public String getmSex() {
        return mSex;
    }
    /**
     * @param mName*/
    public void setmName(String mName) {
        this.mName = mName;
        notifyPropertyChanged( BR.mName );
    }
    @Bindable
    public List<Information> getInformationList() {
        return informationList;
    }

    /**
     * @param mSex */
    public void setmSex(String mSex) {
        this.mSex = mSex;
        notifyPropertyChanged( BR.mSex );
    }
    /**
     * @param mAge */
    public void setmAge(String mAge) {
        this.mAge = mAge;
        notifyPropertyChanged( BR.mAge );
    }
    /**
     * @param informationList */
    public void setInformationList(List<Information> informationList) {
        this.informationList = informationList;
        notifyPropertyChanged( BR.informationList );
    }
}
