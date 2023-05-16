package com.android.testmvvm.test3;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.android.testmvvm.R;
import com.android.testmvvm.uitl.Loge;
import com.squareup.picasso.Picasso;

public class ImageBindingAdapter {


    @BindingAdapter(value = {"netImage", "Imageinfo"}, requireAll = false)
    public static void setImage(ImageView imageView, String url, ModelGirl.Info info) {

        if (url != null && !"".equals(imageView)) {
            /*Glide.with(imageView.getContext())
                    .load(url)
                    .override(300,300)
                    .centerCrop()
                    .into(imageView);*/
            Picasso.get().load(url).placeholder(R.drawable.ic_launcher_background)
                    .resize(info.getWidth(),info.getHeight())
                    .into(imageView);

        }
        if (info != null) {
            Loge.e(info.toString());
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.height = info.getHeight();
            layoutParams.width = info.getWidth();
            imageView.setLayoutParams(layoutParams);
        }

    }

    @BindingAdapter("shuaxin")
    public static void shuaxin(Button button, ModelGirl.Info info) {
        button.setText(info.toString());
    }
}