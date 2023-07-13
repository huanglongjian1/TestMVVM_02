package com.android.test2mvvm.test3;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.android.test2mvvm.BR;
import com.android.test2mvvm.util.Loge;

/**
 * @author markzhai on 16/7/14
 * @version 1.3.0
 */
public class FormModel extends BaseObservable {

    private String mName;
    private String mPassword;

    public FormModel(String name, String password) {
        mName = name;
        mPassword = password;
    }

    @Bindable
    public String getName() {
        return mName;
    }

    public void setName(String name) {

        if (mName.equals(name))
            return;
      //  Loge.e(mName);
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        if (mPassword.equals(password)) {
           // Loge.e(password);
            return;
        }

       // Loge.e(mPassword);
        mPassword = password;
        notifyPropertyChanged(BR.password);


    }
}
