package com.android.test2mvvm.test1.fragment10.binding;

import android.widget.TextView;

import com.android.test2mvvm.R;

public class TestBindingAdapter extends MyBindingAdapter {
    @Override
    public void setText(TextView view, String value) {
        view.setText(value + " test");
    }
    @Override
    public void setTextColor(TextView view, int value) {
        if (value == view.getContext()
                .getResources()
                .getColor(R.color.textColorDay)) {
            view.setTextColor(view.getContext()
                    .getResources()
                    .getColor(R.color.gray));
        }
    }
}