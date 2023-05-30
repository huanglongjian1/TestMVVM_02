package com.android.test2mvvm.test1.fragment10.adapter;

import android.widget.ImageView;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.android.test2mvvm.util.Loge;
import com.squareup.picasso.Picasso;

public class BindingImageBaseURL {
    @BindingAdapter("baseUrl")
    public static void bindingbaseurlimage(ImageView imageView, String baseurl) {
        Loge.e(baseurl);
        Picasso.get().load(baseurl).into(imageView);
    }
}
