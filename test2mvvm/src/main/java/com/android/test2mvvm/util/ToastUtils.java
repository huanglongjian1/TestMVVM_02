package com.android.test2mvvm.util;

import android.widget.Toast;

import com.android.test2mvvm.Test2_App;

public class ToastUtils {
    public static void show(String s){
        show(s,Toast.LENGTH_LONG);
    }
    public static void show(String s, int i) {
        Toast.makeText(Test2_App.getInstance(), s, i).show();

    }
}
