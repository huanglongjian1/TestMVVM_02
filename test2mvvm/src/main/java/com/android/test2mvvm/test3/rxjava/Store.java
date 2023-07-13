package com.android.test2mvvm.test3.rxjava;


import android.content.Context;
import android.content.SharedPreferences;

import com.android.test2mvvm.Test2_App;

import java.util.Random;

public class Store {

    private static final String SP_RX = "sp_rx";
    private static final String TOKEN = "token" + new Random().nextInt(Integer.MAX_VALUE);

    private SharedPreferences mStore;

    private Store() {
        mStore = Test2_App.getInstance().getSharedPreferences(SP_RX, Context.MODE_PRIVATE);
    }

    public static Store getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final Store INSTANCE = new Store();
    }

    public void setToken(String token) {
        mStore.edit().putString(TOKEN, token).apply();
    }

    public String getToken() {
        return mStore.getString(TOKEN, "");
    }
}
