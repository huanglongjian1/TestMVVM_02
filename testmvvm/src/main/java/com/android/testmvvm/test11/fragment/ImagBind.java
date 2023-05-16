package com.android.testmvvm.test11.fragment;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.android.testmvvm.uitl.Loge;
import com.squareup.picasso.Picasso;

public class ImagBind {
    @BindingAdapter("bindImage")
    public static void bindImage(ImageView imageView, String url) {
        Picasso.get().load(url).into(imageView);
        Loge.e("bindimage"+url);
    }
}
