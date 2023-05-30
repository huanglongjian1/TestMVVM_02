package com.android.test2mvvm.test2.adapter;

import android.text.TextUtils;

import androidx.databinding.BindingAdapter;

import com.android.test2mvvm.util.Loge;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

public class BindImage {
    @BindingAdapter("shapeableimageviewurl")
    public static void BindImage(ShapeableImageView imageView, String url) {
        if (!TextUtils.isEmpty(url)) {
            //  Loge.e(url);
            Picasso.get().load(url).into(imageView);
        }
    }

    @BindingAdapter("recyclerview_img_url")
    public static void BindRvItemImage(ShapeableImageView imageView, String url) {
        if (!TextUtils.isEmpty(url)) {
            //  Loge.e(url);
            Picasso.get().load(url).into(imageView);
        }
    }
}
