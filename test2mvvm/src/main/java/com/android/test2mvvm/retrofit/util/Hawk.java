package com.android.test2mvvm.retrofit.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.test2mvvm.Test2_App;
import com.android.test2mvvm.retrofit.enity.HttpResult;
import com.android.test2mvvm.util.Loge;
import com.google.gson.Gson;

import java.lang.reflect.Type;

public abstract class Hawk<T> {
    private SharedPreferences sp;
    private final String FILE_SP_NAME = "SpConfig"; //文件名

    public Hawk() {
        Loge.e("hawk初始化");
        sp = Test2_App.getInstance().getSharedPreferences(FILE_SP_NAME, Context.MODE_PRIVATE);
    }

    public HttpResult<T> get(String key) {
        String value = sp.getString(key, "");
        Loge.e(value);
//        return (T) new Gson().fromJson(value, HttpResult.class);

        Type type = getType();
        HttpResult<T> httpResult = new Gson().fromJson(value, type);
        return httpResult;

    }

    protected abstract Type getType();

    public void put(String key, T t) {
        String value = new Gson().toJson(t);
        sp.edit().putString(key, value).apply();
    }
}
