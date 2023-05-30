package com.android.test2mvvm.test1.fragment10.binding;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public abstract class MyBindingAdapter {
    @BindingAdapter("android:text")
    public abstract  void setText(TextView view, String value);
    @BindingAdapter("android:textColor")
    public abstract void setTextColor(TextView view, int value);
}