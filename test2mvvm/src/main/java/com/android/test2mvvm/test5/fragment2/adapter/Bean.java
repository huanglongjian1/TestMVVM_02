package com.android.test2mvvm.test5.fragment2.adapter;

public class Bean<T> {
    public static final int HEAD = 0;
    public static final int ITEM = 1;
    public static final int FOOT = 2;
    public int type;
    public T data;

    public Bean() {
    }

    public Bean(int type, T data) {
        this.type = type;
        this.data = data;
    }
}
