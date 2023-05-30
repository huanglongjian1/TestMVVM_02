package com.android.test2mvvm.test1.fragment10.binding;

import android.widget.TextView;

import androidx.databinding.adapters.TextViewBindingAdapter;

public class ProductionBindingAdapter extends MyBindingAdapter {
    @Override
    public  void setText(TextView view, String value) {
        view.setText(value);
    }
    @Override
    public void setTextColor(TextView view, int value) {
        view.setTextColor(value);
    }
}