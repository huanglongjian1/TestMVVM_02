package com.android.test2mvvm.test1.fragment;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.android.test2mvvm.R;
import com.squareup.picasso.Picasso;

public class BImage {
    @BindingAdapter("burl")
    public static void burl(ImageView imageView, String url) {
        Picasso.get().load(url).placeholder(R.drawable.ic_launcher_foreground).into(imageView);
    }
}
