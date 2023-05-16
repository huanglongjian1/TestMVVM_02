package com.android.test2mvvm.test1.fragment4;

import android.graphics.Color;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.android.test2mvvm.R;
import com.squareup.picasso.Picasso;

public class RecyclerViewBindingAdapter {
    @BindingAdapter("itemImage")
    public static void setNetWorkImage(ImageView imageView, String imgUrl) {
        if (!TextUtils.isEmpty(imgUrl)) {
            Picasso.get().load(imgUrl).placeholder(R.drawable.ic_launcher_background)
                    .error(R.mipmap.ic_launcher).centerCrop().resize(300, 300)
                    .into(imageView);
        } else {
            imageView.setBackgroundColor(Color.RED);

        }
    }
}
