package com.android.test2mvvm.test1;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.android.test2mvvm.R;
import com.android.test2mvvm.util.Loge;
import com.squareup.picasso.Picasso;

public class BindImage {
    @BindingAdapter("bindimagrul")
    public static void bindImage(ImageView imageView, String url) {
        Loge.e(url);
        Loge.e(imageView.toString());
        Picasso.get().load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
    }
}
