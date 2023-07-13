package com.android.test2mvvm.retrofit.base;

import android.app.Application;


/**
 * Created by helin on 2016/11/11 11:15.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
      //  Hawk.init(this).build();
    }
}
