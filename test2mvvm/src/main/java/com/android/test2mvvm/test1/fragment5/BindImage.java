package com.android.test2mvvm.test1.fragment5;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.android.test2mvvm.util.Loge;
import com.squareup.picasso.Picasso;

public class BindImage {
    @BindingAdapter("imageUrl")
    public static void setImage(ImageView imageView, String url) {
        Picasso.get().load(url).into(imageView);
        Loge.e(url);
    }
}
