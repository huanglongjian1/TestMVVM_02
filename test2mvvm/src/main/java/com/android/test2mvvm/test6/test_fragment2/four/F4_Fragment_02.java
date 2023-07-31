package com.android.test2mvvm.test6.test_fragment2.four;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.test2mvvm.R;
import com.android.test2mvvm.databinding.ImageLayoutBinding;
import com.android.test2mvvm.test6.test_fragment2.Test_Fragment2;
import com.android.test2mvvm.test6.test_fragment2.util.Util_BaseFullBottomSheetFragment;
import com.android.test2mvvm.util.Loge;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class F4_Fragment_02 extends Util_BaseFullBottomSheetFragment<ImageLayoutBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.image_layout;
    }

    Uri image_uri;

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String url = bundle.getString(F4_Fragment_02.class.getSimpleName());
            image_uri = Uri.parse(url);
            Loge.e(url + "-----------");

            binding.test6FragmentImage.setImageURI(image_uri);
            binding.test6FragmentTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // getBehavior().setState(BottomSheetBehavior.STATE_HIDDEN);
                    dismiss();
                }
            });
        }
        binding.test6FragmentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams layoutParams = binding.test6FragmentImage.getLayoutParams();
                layoutParams.height *= 2;
                layoutParams.width *= 2;
                Loge.e(layoutParams.height + ":" + layoutParams.width);
            }
        });

    }

    public static Fragment newInstance(String name) {
        Bundle bundle = new Bundle();
        bundle.putString(F4_Fragment_02.class.getSimpleName(), name);
        Fragment fragment = new F4_Fragment_02();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDataLazyLoad() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Loge.e("F4Fragment_onDestroy");
    }

    public F4Handler getF4Handler() {
        return f4Handler;
    }

    public void setF4Handler(F4Handler f4Handler) {
        this.f4Handler = f4Handler;
    }

    private F4Handler f4Handler = new F4Handler();

    public class F4Handler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                Bitmap bitmap = (Bitmap) msg.obj;
                binding.test6FragmentImage.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
